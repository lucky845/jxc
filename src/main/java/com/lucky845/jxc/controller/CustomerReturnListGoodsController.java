package com.lucky845.jxc.controller;

import com.lucky845.jxc.domain.ServiceVO;
import com.lucky845.jxc.entity.CustomerReturnList;
import com.lucky845.jxc.service.CustomerReturnListGoodsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description 客户退货Controller层
 */
@RestController
@RequestMapping("/customerReturnListGoods")
public class CustomerReturnListGoodsController {

    @Resource
    private CustomerReturnListGoodsService customerReturnListGoodsService;

    /**
     * 保存客户退货单信息
     *
     * @param customerReturnList         客户退货单信息实体
     * @param customerReturnListGoodsStr 客户退货信息JSON字符串
     */
    @RequestMapping("/save")
    @RequiresPermissions(value = "客户退货")
    public ServiceVO save(CustomerReturnList customerReturnList, String customerReturnListGoodsStr) {
        return customerReturnListGoodsService.save(customerReturnList, customerReturnListGoodsStr);
    }

    /**
     * 查询客户退货单
     *
     * @param returnNumber 单号
     * @param customerId   客户ID
     * @param state        付款状态
     * @param sTime        开始时间
     * @param eTime        结束时间

     */
    @RequestMapping("/list")
    @RequiresPermissions(value = {"客户退货查询", "客户统计"}, logical = Logical.OR)
    public Map<String, Object> list(String returnNumber, Integer customerId, Integer state, String sTime,
                                    String eTime) {
        return customerReturnListGoodsService.list(returnNumber, customerId, state, sTime, eTime);
    }

    /**
     * 查询客户退货单商品信息
     *
     * @param customerReturnListId 客户退货单ID

     */
    @RequestMapping("/goodsList")
    @RequiresPermissions(value = {"客户退货查询", "客户统计"}, logical = Logical.OR)
    public Map<String, Object> goodsList(Integer customerReturnListId) {
        return customerReturnListGoodsService.goodsList(customerReturnListId);
    }

    /**
     * 删除客户退货单及商品信息
     *
     * @param customerReturnListId 客户退货单ID

     */
    @RequestMapping("/delete")
    @RequiresPermissions(value = "客户退货查询")
    public ServiceVO delete(Integer customerReturnListId) {
        return customerReturnListGoodsService.delete(customerReturnListId);
    }

    /**
     * 修改客户退货单付款状态
     *
     * @param customerReturnListId 客户退货单ID

     */
    @RequestMapping("/updateState")
    @RequiresPermissions(value = "供应商统计")
    public ServiceVO updateState(Integer customerReturnListId) {
        return customerReturnListGoodsService.updateState(customerReturnListId);
    }

    /**
     * 客户退货商品统计
     *
     * @param sTime       开始时间
     * @param eTime       结束时间
     * @param goodsTypeId 商品类别ID
     * @param codeOrName  编号或商品名称

     */
    @RequestMapping("/count")
    @RequiresPermissions(value = "商品销售统计")
    public String count(String sTime, String eTime, Integer goodsTypeId, String codeOrName) {
        return customerReturnListGoodsService.count(sTime, eTime, goodsTypeId, codeOrName);
    }

}
