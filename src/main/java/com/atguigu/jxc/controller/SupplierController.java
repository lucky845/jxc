package com.atguigu.jxc.controller;

import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
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


}
