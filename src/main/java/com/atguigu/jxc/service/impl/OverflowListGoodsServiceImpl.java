package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.jxc.dao.OverflowListDao;
import com.atguigu.jxc.dao.OverflowListGoodsDao;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.OverflowListGoodsService;
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
public class OverflowListGoodsServiceImpl implements OverflowListGoodsService {

    @Autowired
    private LogService logService;

    @Autowired
    private OverflowListGoodsDao overflowListGoodsDao;

    @Autowired
    private OverflowListDao overflowListDao;

    /**
     * 保存商品报溢单
     *
     * @param overflowList         报溢清单
     * @param overflowListGoodsStr 报溢商品集合
     */
    @Override
    public void save(OverflowList overflowList, String overflowListGoodsStr) {
        List<Map<String, Object>> list = JSONObject.parseObject(overflowListGoodsStr, List.class);
        List<OverflowListGoods> overflowListGoodsList = new ArrayList<>();

        // 需要获取主键,所以需要先保存
        overflowListDao.save(overflowList);

        for (Map<String, Object> map : list) {
            OverflowListGoods overflowListGoods = new OverflowListGoods();
            Integer total = (Integer) map.get("total");
            Integer goodsTypeId = (Integer) map.get("goodsTypeId");
            Integer goodsId = (Integer) map.get("goodsId");
            double price = Double.parseDouble((String) map.get("price"));
            String goodsUnit = (String) map.get("goodsUnit");
            String goodsCode = (String) map.get("goodsCode");
            String goodsModel = (String) map.get("goodsModel");
            String goodsName = (String) map.get("goodsName");
            Integer goodsNum = Integer.parseInt((String) map.get("goodsNum"));
            overflowListGoods.setTotal(total);
            overflowListGoods.setGoodsTypeId(goodsTypeId);
            overflowListGoods.setGoodsId(goodsId);
            overflowListGoods.setPrice(price);
            overflowListGoods.setGoodsUnit(goodsUnit);
            overflowListGoods.setGoodsCode(goodsCode);
            overflowListGoods.setGoodsModel(goodsModel);
            overflowListGoods.setGoodsName(goodsName);
            overflowListGoods.setGoodsNum(goodsNum);
            overflowListGoods.setOverflowListId(overflowList.getOverflowListId());
            overflowListGoodsList.add(overflowListGoods);
        }
        overflowListGoodsDao.save(overflowListGoodsList);
        logService.save(new Log(Log.INSERT_ACTION, "保存报溢单信息"));
    }

    /**
     * 报溢单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     */
    @Override
    public Map<String, Object> list(String sTime, String eTime) {
        Map<String, Object> retMap = new HashMap<>();
        List<OverflowList> overflowListList = overflowListDao.list(sTime, eTime);
        retMap.put("rows", overflowListList);
        return retMap;
    }
}
