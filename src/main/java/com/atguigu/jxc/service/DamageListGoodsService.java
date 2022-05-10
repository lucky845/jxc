package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.DamageList;

public interface DamageListGoodsService {

    /**
     * 保存商品报损单
     *
     * @param damageList         损坏清单
     * @param damageListGoodsStr 损坏商品集合
     */
    void save(DamageList damageList, String damageListGoodsStr);
}
