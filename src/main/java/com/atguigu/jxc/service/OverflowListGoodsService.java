package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.OverflowList;

import java.util.Map;

public interface OverflowListGoodsService {

    /**
     * 保存商品报溢单
     *
     * @param overflowList         报溢清单
     * @param overflowListGoodsStr 报溢商品集合
     */
    void save(OverflowList overflowList, String overflowListGoodsStr);

    /**
     * 报溢单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     */
    Map<String, Object> list(String sTime, String eTime);
}
