package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierDao {
    /**
     * 查询供应商总记录数
     *
     * @param supplierName 供应商名称
     */
    int getSupplierCount(@Param("supplierName") String supplierName);

    /**
     * 查询供应商列表
     *
     * @param offSet       当前页码
     * @param rows         每页记录数
     * @param supplierName 供应商名称
     */
    List<Supplier> getSupplierList(@Param("offSet") int offSet, @Param("rows") Integer rows, @Param("supplierName") String supplierName);

    /**
     * 修改供应商信息
     *
     * @param supplier 供应商信息
     */
    void updateSupplier(Supplier supplier);

    /**
     * 添加供应商信息
     *
     * @param supplier 供应商信息
     */
    void addSupplier(Supplier supplier);
}