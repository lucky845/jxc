package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SupplierDao;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.SupplierService;
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
public class SupplierServiceImpl implements SupplierService {

    @Resource
    private SupplierDao supplierDao;

    @Resource
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

    /**
     * 供应商添加或修改
     *
     * @param supplier 供应商信息
     */
    @Override
    public void save(Supplier supplier) {
        // 1. 如果供应商信息不为空,就是修改,否者就是添加
        if (supplier.getSupplierId() != null) {
            supplierDao.updateSupplier(supplier);
        } else {
            supplierDao.addSupplier(supplier);
        }

    }

    /**
     * 删除供应商
     *
     * @param ids 供应商id
     */
    @Override
    public void delete(String ids) {
        String[] idArray = ids.split(",");
        supplierDao.delete(idArray);
        logService.save(new Log(Log.DELETE_ACTION, "删除供应商信息"));
    }
}
