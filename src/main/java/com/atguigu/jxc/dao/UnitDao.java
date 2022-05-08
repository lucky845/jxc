package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Unit;

import java.util.List;

public interface UnitDao {

    /**
     * 查询所有的商品单位
     */
    List<Unit> list();

}
