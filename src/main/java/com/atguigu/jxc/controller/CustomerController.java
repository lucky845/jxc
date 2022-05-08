package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author lucky845
 * @since 2022年05月08日
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 客户列表分页
     *
     * @param page         当前页码
     * @param rows         每页记录数
     * @param customerName 客户名称
     */
    @ResponseBody
    @PostMapping("/list")
    public Map<String, Object> list(Integer page, Integer rows, String customerName) {
        return customerService.list(page, rows, customerName);
    }

    /**
     * 客户添加或修改
     *
     * @param customer 客户信息
     */
    @ResponseBody
    @PostMapping("/save")
    public ServiceVO save(Customer customer) {
        customerService.save(customer);
        return new ServiceVO(100, "请求成功", null);
    }

    /**
     * 删除客户信息
     *
     * @param ids 客户信息id
     */
    @ResponseBody
    @PostMapping("/delete")
    public ServiceVO delete(String ids) {
        customerService.delete(ids);
        return new ServiceVO(100, "请求成功", null);
    }
}