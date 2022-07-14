package com.lucky845.jxc.dao;

import com.lucky845.jxc.entity.Goods;
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

    /**
     * 查询商品信息
     *
     * @param goodsId 商品id
     */
    Goods getGoods(@Param("goodsId") Integer goodsId);

    /**
     * 删除商品信息
     *
     * @param goodsId 商品id
     */
    void delete(@Param("goodsId") Integer goodsId);

    /**
     * 查询无库存商品的总件数
     *
     * @param nameOrCode        商品名称或编码
     * @param inventoryQuantity 商品库存
     */
    int getNoInventoryQuantityCount(@Param("nameOrCode") String nameOrCode, @Param("inventoryQuantity") int inventoryQuantity);

    /**
     * 查询无库存商品集合
     *
     * @param offSet            当前页码
     * @param rows              每页记录数
     * @param nameOrCode        商品名称或编码
     * @param inventoryQuantity 商品库存
     */
    List<Goods> getNoInventoryQuantityList(@Param("offSet") int offSet, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode, @Param("inventoryQuantity") int inventoryQuantity);

    /**
     * 查询有库存商品的总件数
     *
     * @param nameOrCode        商品名称或编码
     * @param inventoryQuantity 商品库存
     */
    int getHasInventoryQuantityCount(@Param("nameOrCode") String nameOrCode, @Param("inventoryQuantity") int inventoryQuantity);

    /**
     * 查询有库存商品集合
     *
     * @param offSet            当前页码
     * @param rows              每页记录数
     * @param nameOrCode        商品名称或编码
     * @param inventoryQuantity 商品库存
     */
    List<Goods> getHasInventoryQuantityList(@Param("offSet") int offSet, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode, @Param("inventoryQuantity") int inventoryQuantity);

    /**
     * 添加商品期初库存
     *
     * @param goodsId           商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice   成本价
     */
    void saveStock(@Param("goodsId") Integer goodsId, @Param("inventoryQuantity") Integer inventoryQuantity, @Param("purchasingPrice") double purchasingPrice);

    /**
     * 删除商品库存
     *
     * @param goods 商品信息
     */
    void deleteStock(Goods goods);

    /**
     * 查询库存报警商品信息
     */
    List<Goods> listAlarm();

}
