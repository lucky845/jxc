package com.lucky845.jxc.dao;

import com.lucky845.jxc.entity.OverflowList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OverflowListDao {

    /**
     * 保存商品报溢信息
     *
     * @param overflowList 商品报溢信息
     */
    void save(OverflowList overflowList);

    /**
     * 报溢单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     */
    List<OverflowList> list(@Param("sTime") String sTime, @Param("eTime") String eTime);
}
