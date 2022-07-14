package com.lucky845.jxc.controller;

import com.lucky845.jxc.domain.ServiceVO;
import com.lucky845.jxc.domain.SuccessCode;
import com.lucky845.jxc.entity.Goods;
import com.lucky845.jxc.service.GoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description 商品信息Controller
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 分页查询商品库存信息
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param codeOrName  商品编码或名称
     * @param goodsTypeId 商品类别ID
     */
    @PostMapping("/listInventory")
    public Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        return goodsService.listInventory(page, rows, codeOrName, goodsTypeId);
    }


    /**
     * 分页查询商品信息
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param goodsName   商品名称
     * @param goodsTypeId 商品类别ID
     */
    @PostMapping("/list")
    public Map<String, Object> list(Integer page, Integer rows, String goodsName, Integer goodsTypeId) {
        return goodsService.list(page, rows, goodsName, goodsTypeId);
    }

    /**
     * 生成商品编码
     *

     */
    @PostMapping("/getCode")
    @RequiresPermissions(value = "商品管理")
    public ServiceVO getCode() {
        return goodsService.getCode();
    }

    /**
     * 添加或修改商品信息
     *
     * @param goods 商品信息实体
     */
    @PostMapping("/save")
    public ServiceVO save(Goods goods) {
        goodsService.save(goods);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    /**
     * 删除商品信息
     *
     * @param goodsId 商品ID
     */
    @PostMapping("/delete")
    public ServiceVO delete(Integer goodsId) {
        goodsService.delete(goodsId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    /**
     * 分页查询无库存商品信息
     *
     * @param page       当前页
     * @param rows       每页显示条数
     * @param nameOrCode 商品名称或商品编码
     */
    @PostMapping("/getNoInventoryQuantity")
    public Map<String, Object> getNoInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        return goodsService.getNoInventoryQuantity(page, rows, nameOrCode);
    }


    /**
     * 分页查询有库存商品信息
     *
     * @param page       当前页
     * @param rows       每页显示条数
     * @param nameOrCode 商品名称或商品编码
     */
    @PostMapping("/getHasInventoryQuantity")
    public Map<String, Object> getHasInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        return goodsService.getHasInventoryQuantity(page, rows, nameOrCode);
    }

    /**
     * 添加商品期初库存
     *
     * @param goodsId           商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice   成本价
     */
    @PostMapping("/saveStock")
    public ServiceVO saveStock(Integer goodsId, Integer inventoryQuantity, double purchasingPrice) {
        goodsService.saveStock(goodsId, inventoryQuantity, purchasingPrice);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    /**
     * 删除商品库存
     *
     * @param goodsId 商品ID
     */
    @PostMapping("/deleteStock")
    public ServiceVO deleteStock(Integer goodsId) {
        goodsService.deleteStock(goodsId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    /**
     * 查询库存报警商品信息
     */
    @PostMapping("/listAlarm")
    public Map<String, Object> listAlarm() {
        return goodsService.listAlarm();
    }

}
