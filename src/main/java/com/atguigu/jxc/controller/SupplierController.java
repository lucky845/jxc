package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 基础资料模块--供应商管理
 *
 * @author lucky845
 * @since 2022年05月08日
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private SupplierService supplierService;

    /**
     * 分页查询供应商
     *
     * @param page         当前页码
     * @param rows         每页记录数
     * @param supplierName 供应商名称
     */
    @ResponseBody
    @PostMapping("/list")
    public Map<String, Object> list(Integer page, Integer rows, String supplierName) {
        return supplierService.list(page, rows, supplierName);
    }

    /**
     * 供应商添加或修改
     *
     * @param supplier 供应商信息
     */
    @ResponseBody
    @PostMapping("/save")
    public ServiceVO save(Supplier supplier) {
        supplierService.save(supplier);
        return new ServiceVO(100, "请求成功", null);
    }

    /**
     * 删除供应商
     *
     * @param ids 供应商id
     */
    @ResponseBody
    @PostMapping("/delete")
    public ServiceVO delete(String ids) {
        supplierService.delete(ids);
        return new ServiceVO(100, "请求成功", null);
    }

}
