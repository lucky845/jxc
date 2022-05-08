package com.atguigu.jxc.controller;

import com.atguigu.jxc.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基础资料模块--商品类别
 *
 * @author lucky845
 * @since 2022年05月08日
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 查询商品所有分类树
     */
    @ResponseBody
    @PostMapping("/loadGoodsType")
    public String loadGoodsType() {
        return goodsTypeService.loadGoodsType();
    }

}