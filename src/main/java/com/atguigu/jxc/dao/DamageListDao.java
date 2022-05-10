package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DamageListDao {

    /**
     * 保存报损单信息
     *
     * @param damageList 报损单信息
     */
    void save(DamageList damageList);

    /**
     * 报损单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     */
    List<DamageList> list(@Param("sTime") String sTime, @Param("eTime") String eTime);

    /**
     * 查询报损单商品信息
     *
     * @param damageListId 报损单id
     */
    List<DamageListGoods> goodsList(@Param("damageListId") Integer damageListId);
}
