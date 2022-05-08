package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SupplierDao;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.SupplierService;
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
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private LogService logService;

    /**
     * 分页查询供应商
     *
     * @param page         当前页码
     * @param rows         每页记录数
     * @param supplierName 供应商名称
     */
    @Override
    public Map<String, Object> list(Integer page, Integer rows, String supplierName) {
        Map<String, Object> retMap = new HashMap<>();
        int total = supplierDao.getSupplierCount(supplierName);
        page = page == 0 ? 1 : page;
        int offSet = (page - 1) * rows;
        List<Supplier> supplierList = supplierDao.getSupplierList(offSet, rows, supplierName);

        logService.save(new Log(Log.SELECT_ACTION, "分页查询供应商信息"));

        retMap.put("total", total);
        retMap.put("rows", supplierList);
        return retMap;
    }
}
