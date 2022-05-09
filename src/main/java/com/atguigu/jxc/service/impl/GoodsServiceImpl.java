package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Goods> goodsList = goodsDao.getGoodsList(offSet, rows, codeOrName, goodsTypeId);

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
        List<Goods> goodsList = goodsDao.getGoodsList(offSet, rows, goodsName, goodsTypeId);

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

    }
}
