package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.GoodsType;

import java.util.List;

/**
 * @description 商品类别
 */
public interface GoodsTypeDao {

    Integer updateGoodsTypeState(GoodsType parentGoodsType);

    /**
     * 查询商品分类列表
     */
    List<GoodsType> getGoodsTypeList();

    /**
     * 新增分类
     *
     * @param goodsType 分类信息
     */
    void addGoodsType(GoodsType goodsType);
}
