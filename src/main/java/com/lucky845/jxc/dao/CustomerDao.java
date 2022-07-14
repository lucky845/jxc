package com.lucky845.jxc.dao;

import com.lucky845.jxc.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerDao {

    /**
     * 查询客户总数量
     *
     * @param customerName 客户名称
     */
    int getCustomerCount(@Param("customerName") String customerName);

    /**
     * 查询客户列表
     *
     * @param offset       当前页数
     * @param rows         每页记录数
     * @param customerName 客户名称
     */
    List<Customer> getCustomerList(@Param("offset") int offset, @Param("rows") Integer rows, @Param("customerName") String customerName);

    /**
     * 修改客户信息
     *
     * @param customer 客户信息
     */
    void update(Customer customer);

    /**
     * 新增客户信息
     *
     * @param customer 客户信息
     */
    void addCustomer(Customer customer);

    /**
     * 删除客户信息
     *
     * @param idArray 客户id
     */
    void delete(@Param("idArray") String[] idArray);
}
