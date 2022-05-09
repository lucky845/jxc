package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Goods;

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
}
