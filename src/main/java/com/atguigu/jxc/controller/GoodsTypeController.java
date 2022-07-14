package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.service.GoodsTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 基础资料模块--商品类别
 *
 * @author lucky845
 * @since 2022年05月08日
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Resource
    private GoodsTypeService goodsTypeService;

    /**
     * 查询商品所有分类树
     */
    @ResponseBody
    @PostMapping("/loadGoodsType")
    public String loadGoodsType() {
        return goodsTypeService.loadGoodsType();
    }

    /**
     * 新增分类
     *
     * @param goodsTypeName 商品类型名称
     * @param pId           父id
     */
    @ResponseBody
    @PostMapping("/save")
    public ServiceVO save(String goodsTypeName, Integer pId) {
        goodsTypeService.save(goodsTypeName, pId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    /**
     * 删除分类
     *
     * @param goodsTypeId 商品分类id
     */
    @ResponseBody
    @PostMapping("/delete")
    public ServiceVO delete(Integer goodsTypeId) {
        goodsTypeService.delete(goodsTypeId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

}
