package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerDao;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.CustomerService;
import com.atguigu.jxc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucky845
 * @since 2022年05月08日
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LogService logService;

    /**
     * 客户列表分页
     *
     * @param page         当前页码
     * @param rows         每页记录数
     * @param customerName 客户名称
     */
    @Override
    public Map<String, Object> list(Integer page, Integer rows, String customerName) {
        Map<String, Object> retMap = new HashMap<>();
        int total = customerDao.getCustomerCount(customerName);
        page = page == 0 ? 1 : page;
        int offset = (page - 1) * rows;
        List<Customer> customerList = customerDao.getCustomerList(offset, rows, customerName);

        logService.save(new Log(Log.SELECT_ACTION, "查询客户列表分页信息"));

        retMap.put("total", total);
        retMap.put("rows", customerList);
        return retMap;
    }
}
