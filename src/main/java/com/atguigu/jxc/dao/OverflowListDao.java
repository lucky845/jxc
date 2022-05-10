package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowList;

public interface OverflowListDao {

    /**
     * 保存商品报溢信息
     *
     * @param overflowList 商品报溢信息
     */
    void save(OverflowList overflowList);
}
