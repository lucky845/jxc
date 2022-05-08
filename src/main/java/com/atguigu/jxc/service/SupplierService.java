package com.atguigu.jxc.service;

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
}
