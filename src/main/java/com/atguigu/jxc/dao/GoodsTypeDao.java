package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.GoodsType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description 商品类别
 */
@Repository
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

    /**
     * 删除商品分类
     *
     * @param goodsTypeId 商品分类id
     */
    void delete(@Param("goodsTypeId") Integer goodsTypeId);

    /**
     * 根据商品分类id获取商品分类信息
     *
     * @param goodsTypeId 商品分类id
     */
    GoodsType getGoodsTypeById(@Param("") Integer goodsTypeId);
}
