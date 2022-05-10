package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowListGoods;

import java.util.List;

public interface OverflowListGoodsDao {

    /**
     * 保存报溢商品信息
     *
     * @param overflowListGoodsList 报溢商品信息
     */
    void save(List<OverflowListGoods> overflowListGoodsList);
}
