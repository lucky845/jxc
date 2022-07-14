package com.lucky845.jxc.dao;

import com.lucky845.jxc.entity.OverflowListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OverflowListGoodsDao {

    /**
     * 保存报溢商品信息
     *
     * @param overflowListGoodsList 报溢商品信息
     */
    void save(List<OverflowListGoods> overflowListGoodsList);

    /**
     * 查询报溢单商品信息
     *
     * @param overflowListId 报溢单id
     */
    List<OverflowListGoods> goodsList(@Param("overflowListId") Integer overflowListId);
}
