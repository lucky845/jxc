package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageListGoods;

import java.util.List;

public interface DamageListGoodsDao {

    /**
     * 保存报损商品信息
     *
     * @param damageListGoodsList 报损商品列表
     */
    void save(List<DamageListGoods> damageListGoodsList);
}
