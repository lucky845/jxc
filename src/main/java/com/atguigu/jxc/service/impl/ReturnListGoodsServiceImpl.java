package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.dao.ReturnListGoodsDao;
import com.atguigu.jxc.dao.UserDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.ReturnListGoodsService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 */
@Service
public class ReturnListGoodsServiceImpl implements ReturnListGoodsService {

    /*@Autowired
    private LogService logService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ReturnListGoodsDao returnListGoodsDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Override
    public ServiceVO save(ReturnList returnList, String returnListGoodsStr) {

        // 使用谷歌Gson将JSON字符串数组转换成具体的集合
        Gson gson = new Gson();

        List<ReturnListGoods> returnListGoodsList = gson.fromJson(returnListGoodsStr,new TypeToken<List<ReturnListGoods>>(){}.getType());

        // 设置当前操作用户
        User currentUser = userDao.findUserByName((String) SecurityUtils.getSubject().getPrincipal());

        returnList.setUserId(currentUser.getUserId());

        // 保存退货单信息
        returnListGoodsDao.saveReturnList(returnList);

        // 保存退货单商品信息
        for(ReturnListGoods returnListGoods : returnListGoodsList){

            returnListGoods.setReturnListId(returnList.getReturnListId());

            returnListGoods.setGoodsTypeId(goodsDao.findByGoodsId(returnListGoods.getGoodsId()).getGoodsTypeId());

            returnListGoodsDao.saveReturnListGoods(returnListGoods);

            // 修改商品库存，状态
            Goods goods = goodsDao.findByGoodsId(returnListGoods.getGoodsId());

            goods.setInventoryQuantity(goods.getInventoryQuantity()-returnListGoods.getGoodsNum());

            goods.setState(2);

            goodsDao.updateGoods(goods);

        }

        // 保存日志
        logService.save(new Log(Log.INSERT_ACTION, "新增退货单："+returnList.getReturnNumber()));

        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @Override
    public Map<String, Object> list(String returnNumber, Integer supplierId, Integer state, String sTime, String eTime) {
        Map<String,Object> result = new HashMap<>();

        List<ReturnList> returnListList = returnListGoodsDao.getReturnlist(returnNumber, supplierId, state, sTime, eTime);

        logService.save(new Log(Log.SELECT_ACTION, "退货单据查询"));

        result.put("rows", returnListList);

        return result;
    }

    @Override
    public Map<String, Object> goodsList(Integer returnListId) {
        Map<String,Object> map = new HashMap<>();

        List<ReturnListGoods> returnListGoodsList = returnListGoodsDao.getReturnListGoodsByReturnListId(returnListId);

        logService.save(new Log(Log.SELECT_ACTION, "退货单商品信息查询"));

        map.put("rows", returnListGoodsList);

        return map;
    }

    @Override
    public ServiceVO delete(Integer returnListId) {

        logService.save(new Log(Log.DELETE_ACTION, "删除退货单："+returnListGoodsDao.getReturnList(returnListId).getReturnNumber()));

        returnListGoodsDao.deleteReturnListGoodsByReturnListId(returnListId);

        returnListGoodsDao.deleteReturnListById(returnListId);

        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @Override
    public ServiceVO updateState(Integer returnListId) {

        returnListGoodsDao.updateState(returnListId);

        logService.save(new Log(Log.DELETE_ACTION, "支付结算退货单：" + returnListGoodsDao.getReturnList(returnListId).getReturnNumber()));

        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @Override
    public String count(String sTime, String eTime, Integer goodsTypeId, String codeOrName) {

        JsonArray result = new JsonArray();

        try {

            List<ReturnList> returnListList = returnListGoodsDao.getReturnlist(null, null, null, sTime, eTime);

            for(ReturnList pl : returnListList){

                List<ReturnListGoods> returnListGoodsList = returnListGoodsDao
                        .getReturnListGoods(pl.getReturnListId(), goodsTypeId, codeOrName);

                for(ReturnListGoods pg : returnListGoodsList){

                    JsonObject obj = new JsonObject();

                    obj.addProperty("number", pl.getReturnNumber());

                    obj.addProperty("date", pl.getReturnDate());

                    obj.addProperty("supplierName", pl.getSupplierName());

                    obj.addProperty("code", pg.getGoodsCode());

                    obj.addProperty("name", pg.getGoodsName());

                    obj.addProperty("model", pg.getGoodsModel());

                    obj.addProperty("goodsType", goodsTypeDao.getGoodsTypeById(pg.getGoodsTypeId()).getGoodsTypeName());

                    obj.addProperty("unit", pg.getGoodsUnit());

                    obj.addProperty("price", pg.getPrice());

                    obj.addProperty("num", pg.getGoodsNum());

                    obj.addProperty("total", pg.getTotal());

                    result.add(obj);

                }
            }

            logService.save(new Log(Log.SELECT_ACTION, "退货商品统计查询"));

        } catch (Exception e) {

            e.printStackTrace();

        }

        return result.toString();
    }*/
}
