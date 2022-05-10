package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.dao.PurchaseListGoodsDao;
import com.atguigu.jxc.dao.SaleListGoodsDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.GoodsTypeService;
import com.atguigu.jxc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private SaleListGoodsDao saleListGoodsDao;

    @Autowired
    private PurchaseListGoodsDao purchaseListGoodsDao;

    @Autowired
    private GoodsTypeService goodsTypeService;

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Autowired
    private LogService logService;

    @Override
    public ServiceVO getCode() {

        // 获取当前商品最大编码
        String code = goodsDao.getMaxCode();

        // 在现有编码上加1
        Integer intCode = Integer.parseInt(code) + 1;

        // 将编码重新格式化为4位数字符串形式
        String unitCode = intCode.toString();

        for (int i = 4; i > intCode.toString().length(); i--) {

            unitCode = "0" + unitCode;

        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, unitCode);
    }

    /**
     * 分页查询商品库存信息
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param codeOrName  商品编码或名称
     * @param goodsTypeId 商品类别ID
     */
    @Override
    public Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        Map<String, Object> retMap = new HashMap<>();
        int total = goodsDao.getGoodsCount(codeOrName, goodsTypeId);
        page = page == 0 ? 1 : page;
        int offSet = (page - 1) * rows;
        // 查询所有的子节点
        List<Integer> childrenIdList = null;
        List<Goods> goodsList = new ArrayList<>();
        List<Goods> temp = new ArrayList<>();
        if (goodsTypeId != null && goodsTypeId != 1) {
            List<GoodsType> allGoodsTypeList = goodsTypeDao.getGoodsTypeList();
            childrenIdList = goodsTypeService.getChildrenIdList(goodsTypeId, allGoodsTypeList);
            if (childrenIdList.size() == 0) {
                // 没有子节点
                goodsList = goodsDao.getGoodsList(offSet, rows, codeOrName, goodsTypeId);
            } else {
                for (Integer childrenId : childrenIdList) {
                    temp = goodsDao.getGoodsList(offSet, rows, codeOrName, childrenId);
                    goodsList.addAll(temp);
                }
            }
        } else {
            goodsList = goodsDao.getGoodsList(offSet, rows, codeOrName, null);
        }

        logService.save(new Log(Log.SELECT_ACTION, "分页查询商品库存信息"));

        retMap.put("total", total);
        retMap.put("rows", goodsList);
        return retMap;
    }

    /**
     * 分页查询商品信息
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param goodsName   商品名称
     * @param goodsTypeId 商品类别ID
     */
    @Override
    public Map<String, Object> list(Integer page, Integer rows, String goodsName, Integer goodsTypeId) {
        Map<String, Object> retMap = new HashMap<>();
        int total = goodsDao.getGoodsCount(goodsName, goodsTypeId);
        page = page == 0 ? 1 : page;
        int offSet = (page - 1) * rows;
        // 查询所有的子节点
        List<Integer> childrenIdList = null;
        List<Goods> goodsList = new ArrayList<>();
        List<Goods> temp = new ArrayList<>();
        if (goodsTypeId != null && goodsTypeId != 1) {
            List<GoodsType> allGoodsTypeList = goodsTypeDao.getGoodsTypeList();
            childrenIdList = goodsTypeService.getChildrenIdList(goodsTypeId, allGoodsTypeList);
            if (childrenIdList.size() == 0) {
                // 没有子节点
                goodsList = goodsDao.getGoodsList(offSet, rows, goodsName, goodsTypeId);
            } else {
                for (Integer childrenId : childrenIdList) {
                    temp = goodsDao.getGoodsList(offSet, rows, goodsName, childrenId);
                    goodsList.addAll(temp);
                }
            }
        } else {
            goodsList = goodsDao.getGoodsList(offSet, rows, goodsName, null);
        }

        logService.save(new Log(Log.SELECT_ACTION, "分页查询所有商品信息"));

        retMap.put("total", total);
        retMap.put("rows", goodsList);
        return retMap;
    }

    /**
     * 商品添加或修改
     *
     * @param goods 商品对象
     */
    @Override
    public void save(Goods goods) {
        if (goods.getGoodsId() != null) {
            // 修改
            goodsDao.updateGoods(goods);
        } else {
            // 添加
            goods.setState(0);
            goods.setInventoryQuantity(0);
            goodsDao.addGoods(goods);
        }
    }

    /**
     * 删除商品信息
     *
     * @param goodsId 商品id
     */
    @Override
    public void delete(Integer goodsId) {
        // 要判断商品状态(入库)、有进货和销售单据的不能删除
        Goods goods = goodsDao.getGoods(goodsId);
        if (goods.getState() != 1 && goods.getState() != 2) {
            goodsDao.delete(goodsId);
            logService.save(new Log(Log.DELETE_ACTION, "删除商品信息"));
        }
    }

    /**
     * 分页查询无库存商品信息
     *
     * @param page       当前页
     * @param rows       每页显示条数
     * @param nameOrCode 商品名称或商品编码
     */
    @Override
    public Map<String, Object> getNoInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        Map<String, Object> retMap = new HashMap<>();
        int total = goodsDao.getNoInventoryQuantityCount(nameOrCode, 0);
        page = page == 0 ? 1 : page;
        int offSet = (page - 1) * rows;
        List<Goods> goodsList = goodsDao.getNoInventoryQuantityList(offSet, rows, nameOrCode, 0);

        logService.save(new Log(Log.SELECT_ACTION, "分页查询无库存商品信息"));

        retMap.put("total", total);
        retMap.put("rows", goodsList);
        return retMap;
    }

    /**
     * 分页查询有库存商品信息
     *
     * @param page       当前页
     * @param rows       每页显示条数
     * @param nameOrCode 商品名称或商品编码
     */
    @Override
    public Map<String, Object> getHasInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        Map<String, Object> retMap = new HashMap<>();
        int total = goodsDao.getHasInventoryQuantityCount(nameOrCode, 0);
        page = page == 0 ? 1 : page;
        int offSet = (page - 1) * rows;
        List<Goods> goodsList = goodsDao.getHasInventoryQuantityList(offSet, rows, nameOrCode, 0);

        logService.save(new Log(Log.SELECT_ACTION, "分页查询有库存商品信息"));

        retMap.put("total", total);
        retMap.put("rows", goodsList);
        return retMap;
    }

    /**
     * 添加商品期初库存
     *
     * @param goodsId           商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice   成本价
     */
    @Override
    public void saveStock(Integer goodsId, Integer inventoryQuantity, double purchasingPrice) {
        goodsDao.saveStock(goodsId, inventoryQuantity, purchasingPrice);
        logService.save(new Log(Log.INSERT_ACTION, "添加商品初期库存"));
    }

    /**
     * 删除商品库存
     *
     * @param goodsId 商品ID
     */
    @Override
    public void deleteStock(Integer goodsId) {
        // 要判断商品状态(入库)、有进货和销售单据的不能删除
        Goods goods = goodsDao.getGoods(goodsId);
        if (goods.getState() != 1 && goods.getState() != 2) {
            goods.setInventoryQuantity(0);
            goods.setPurchasingPrice(0.0);
            goodsDao.deleteStock(goods);
            logService.save(new Log(Log.DELETE_ACTION, "删除商品库存信息"));
        }
    }

    /**
     * 查询库存报警商品信息
     */
    @Override
    public Map<String, Object> listAlarm() {
        Map<String, Object> retMap = new HashMap<>();
        List<Goods> goodsList = goodsDao.listAlarm();
        logService.save(new Log(Log.SELECT_ACTION, "查询库存报警商品信息"));
        retMap.put("rows", goodsList);
        return retMap;
    }
}
