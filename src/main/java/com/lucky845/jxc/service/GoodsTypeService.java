package com.lucky845.jxc.service;

import com.lucky845.jxc.entity.GoodsType;

import java.util.List;

public interface GoodsTypeService {

    /**
     * 查询商品所有分类
     */
    String loadGoodsType();

    /**
     * 新增分类
     *
     * @param goodsTypeName 商品类型名称
     * @param pId           父id
     */
    void save(String goodsTypeName, Integer pId);

    /**
     * 删除分类
     *
     * @param goodsTypeId 商品分类id
     */
    void delete(Integer goodsTypeId);

    /**
     * 根据父id查询所有的子id
     *
     * @param parentId 父id
     */
    List<Integer> getChildrenIdList(Integer parentId, List<GoodsType> allGoodsTypeList);
}
