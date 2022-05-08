package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.Customer;

import java.util.Map;

public interface CustomerService {

    /**
     * 客户列表分页
     *
     * @param page         当前页码
     * @param rows         每页记录数
     * @param customerName 客户名称
     */
    Map<String, Object> list(Integer page, Integer rows, String customerName);

    /**
     * 添加或修改客户信息
     *
     * @param customer 客户信息
     */
    void save(Customer customer);
}
