package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.DamageList;

import java.util.Map;

public interface DamageListGoodsService {

    /**
     * 保存商品报损单
     *
     * @param damageList         损坏清单
     * @param damageListGoodsStr 损坏商品集合
     */
    void save(DamageList damageList, String damageListGoodsStr);

    /**
     * 报损单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     */
    Map<String, Object> list(String sTime, String eTime);
}
