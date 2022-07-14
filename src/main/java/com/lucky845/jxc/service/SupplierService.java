package com.lucky845.jxc.service;

import com.lucky845.jxc.entity.Supplier;

import java.util.Map;

public interface SupplierService {

    /**
     * 分页查询供应商
     *
     * @param page         当前页码
     * @param rows         每页记录数
     * @param supplierName 供应商名称
     */
    Map<String, Object> list(Integer page, Integer rows, String supplierName);

    /**
     * 供应商添加或修改
     *
     * @param supplier 供应商信息
     */
    void save(Supplier supplier);

    /**
     * 删除供应商
     *
     * @param ids 供应商id
     */
    void delete(String ids);
}
