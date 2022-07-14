package com.lucky845.jxc.dao;

import com.lucky845.jxc.entity.DamageListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DamageListGoodsDao {

    /**
     * 保存报损商品信息
     *
     * @param damageListGoodsList 报损商品列表
     */
    void save(List<DamageListGoods> damageListGoodsList);

    /**
     * 查询报损单商品信息
     *
     * @param damageListId 报损单id
     */
    List<DamageListGoods> goodsList(@Param("damageListId") Integer damageListId);
}
