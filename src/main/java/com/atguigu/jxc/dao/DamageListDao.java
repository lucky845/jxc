package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageList;

public interface DamageListDao {

    /**
     * 保存报损单信息
     *
     * @param damageList 报损单信息
     */
    void save(DamageList damageList);
}
