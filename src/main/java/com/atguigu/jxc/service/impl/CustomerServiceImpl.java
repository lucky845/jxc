package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerDao;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.CustomerService;
import com.atguigu.jxc.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucky845
 * @since 2022年05月08日
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDao customerDao;

    @Resource
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

    /**
     * 添加或修改客户信息
     *
     * @param customer 客户信息
     */
    @Override
    public void save(Customer customer) {
        if (customer.getCustomerId() != null) {
            customerDao.update(customer);
            logService.save(new Log(Log.UPDATE_ACTION, "修改客户信息"));
        } else {
            customerDao.addCustomer(customer);
            logService.save(new Log(Log.INSERT_ACTION, "新增客户信息"));
        }

    }

    /**
     * 删除客户信息
     *
     * @param ids 客户信息id
     */
    @Override
    public void delete(String ids) {
        String[] idArray = ids.split(",");
        customerDao.delete(idArray);
        logService.save(new Log(Log.DELETE_ACTION, "删除客户信息"));
    }
}
