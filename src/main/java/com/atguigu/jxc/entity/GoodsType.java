package com.atguigu.jxc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 商品类别
 */
@Data
@NoArgsConstructor
public class GoodsType {

    private Integer goodsTypeId;
    private String goodsTypeName;
    private Integer pId;
    private Integer goodsTypeState;
    private List<Map<String, Object>> childrenList;

    public GoodsType(String goodsTypeName, Integer goodsTypeState, Integer pId) {
        this.goodsTypeName = goodsTypeName;
        this.goodsTypeState = goodsTypeState;
        this.pId = pId;
    }
}
