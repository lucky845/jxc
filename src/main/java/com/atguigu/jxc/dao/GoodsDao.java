package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 商品信息
 */
public interface GoodsDao {


    String getMaxCode();

    /**
     * 查询总记录数
     *
     * @param codeOrName  商品编码或名称
     * @param goodsTypeId 商品类别id
     */
    int getGoodsCount(@Param("codeOrName") String codeOrName, @Param("goodsTypeId") Integer goodsTypeId);

    /**
     * 查询商品分页列表
     *
     * @param offSet      当前页码
     * @param rows        每页记录数
     * @param codeOrName  商品编码或名称
     * @param goodsTypeId 商品类别id
     */
    List<Goods> getGoodsList(@Param("offSet") int offSet, @Param("rows") Integer rows, @Param("codeOrName") String codeOrName, @Param("goodsTypeId") Integer goodsTypeId);

    /**
     * 修改商品信息
     *
     * @param goods 商品信息
     */
    void updateGoods(Goods goods);

    /**
     * 添加商品信息
     *
     * @param goods 商品信息
     */
    void addGoods(Goods goods);
}
