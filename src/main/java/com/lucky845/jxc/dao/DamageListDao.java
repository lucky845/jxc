package com.lucky845.jxc.dao;

import com.lucky845.jxc.entity.DamageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DamageListDao {

    /**
     * 保存报损单信息
     *
     * @param damageList 报损单信息
     */
    void save(DamageList damageList);

    /**
     * 报损单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     */
    List<DamageList> list(@Param("sTime") String sTime, @Param("eTime") String eTime);
}
