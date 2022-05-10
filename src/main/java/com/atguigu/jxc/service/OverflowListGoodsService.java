package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.OverflowList;

public interface OverflowListGoodsService {

    /**
     * 保存商品报溢单
     *
     * @param overflowList         报溢清单
     * @param overflowListGoodsStr 报溢商品集合
     */
    void save(OverflowList overflowList, String overflowListGoodsStr);

}
