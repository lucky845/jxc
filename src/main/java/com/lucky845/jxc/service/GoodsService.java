package com.lucky845.jxc.service;

import com.lucky845.jxc.domain.ServiceVO;
import com.lucky845.jxc.entity.Goods;

import java.util.Map;

public interface GoodsService {


    ServiceVO getCode();

    /**
     * 分页查询商品库存信息
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param codeOrName  商品编码或名称
     * @param goodsTypeId 商品类别ID
     */
    Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);

    /**
     * 分页查询商品信息
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param goodsName   商品名称
     * @param goodsTypeId 商品类别ID
     */
    Map<String, Object> list(Integer page, Integer rows, String goodsName, Integer goodsTypeId);

    /**
     * 商品添加或修改
     *
     * @param goods 商品对象
     */
    void save(Goods goods);

    /**
     * 删除商品信息
     *
     * @param goodsId 商品id
     */
    void delete(Integer goodsId);

    /**
     * 分页查询无库存商品信息
     *
     * @param page       当前页
     * @param rows       每页显示条数
     * @param nameOrCode 商品名称或商品编码
     */
    Map<String, Object> getNoInventoryQuantity(Integer page, Integer rows, String nameOrCode);

    /**
     * 分页查询有库存商品信息
     *
     * @param page       当前页
     * @param rows       每页显示条数
     * @param nameOrCode 商品名称或商品编码
     */
    Map<String, Object> getHasInventoryQuantity(Integer page, Integer rows, String nameOrCode);

    /**
     * 添加商品期初库存
     *
     * @param goodsId           商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice   成本价
     */
    void saveStock(Integer goodsId, Integer inventoryQuantity, double purchasingPrice);

    /**
     * 删除商品库存
     *
     * @param goodsId 商品ID
     */
    void deleteStock(Integer goodsId);

    /**
     * 查询库存报警商品信息
     */
    Map<String, Object> listAlarm();

}
