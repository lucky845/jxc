package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.jxc.dao.DamageListDao;
import com.atguigu.jxc.dao.DamageListGoodsDao;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.DamageListGoodsService;
import com.atguigu.jxc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucky845
 * @since 2022年05月10日
 */
@Service
public class DamageListGoodsServiceImpl implements DamageListGoodsService {

    @Autowired
    private DamageListGoodsDao damageListGoodsDao;

    @Autowired
    private DamageListDao damageListDao;

    @Autowired
    private LogService logService;

    /**
     * 保存商品报损单
     *
     * @param damageList         损坏清单
     * @param damageListGoodsStr 损坏商品集合
     */
    @Override
    public void save(DamageList damageList, String damageListGoodsStr) {

        List<Map<String, Object>> list = JSONObject.parseObject(damageListGoodsStr, List.class);
        ArrayList<DamageListGoods> damageListGoodsList = new ArrayList<>();

        // 需要获取主键,所以需要先保存
        damageListDao.save(damageList);

        for (Map<String, Object> map : list) {
            DamageListGoods damageListGoods = new DamageListGoods();
            Integer total = (Integer) map.get("total");
            Integer goodsTypeId = (Integer) map.get("goodsTypeId");
            Integer goodsId = (Integer) map.get("goodsId");
            double price = Double.parseDouble((String) map.get("price"));
            String goodsUnit = (String) map.get("goodsUnit");
            String goodsCode = (String) map.get("goodsCode");
            String goodsModel = (String) map.get("goodsModel");
            String goodsName = (String) map.get("goodsName");
            Integer goodsNum = Integer.parseInt((String) map.get("goodsNum"));
            damageListGoods.setTotal(total);
            damageListGoods.setGoodsTypeId(goodsTypeId);
            damageListGoods.setGoodsId(goodsId);
            damageListGoods.setPrice(price);
            damageListGoods.setGoodsUnit(goodsUnit);
            damageListGoods.setGoodsCode(goodsCode);
            damageListGoods.setGoodsModel(goodsModel);
            damageListGoods.setGoodsName(goodsName);
            damageListGoods.setGoodsNum(goodsNum);
            damageListGoods.setDamageListId(damageList.getDamageListId());
            damageListGoodsList.add(damageListGoods);
        }
        damageListGoodsDao.save(damageListGoodsList);
        logService.save(new Log(Log.INSERT_ACTION, "保存报损单信息"));
    }

    /**
     * 报损单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     */
    @Override
    public Map<String, Object> list(String sTime, String eTime) {
        Map<String, Object> retMap = new HashMap<>();
        List<DamageList> damageListList = damageListDao.list(sTime, eTime);
        retMap.put("rows", damageListList);
        return retMap;
    }
}
