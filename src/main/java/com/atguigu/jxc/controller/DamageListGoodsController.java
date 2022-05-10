package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.DamageListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author lucky845
 * @since 2022年05月10日
 */
@RestController
@RequestMapping("/damageListGoods")
public class DamageListGoodsController {

    @Autowired
    private DamageListGoodsService damageListGoodsService;

    /**
     * 保存商品报损单
     *
     * @param damageList         损坏清单
     * @param damageListGoodsStr 损坏商品集合
     */
    @PostMapping("/save")
    public ServiceVO save(DamageList damageList, String damageListGoodsStr, HttpSession session) {
        // 从session中获取user信息
        User user = (User) session.getAttribute("currentUser");
        damageList.setUserId(user.getUserId());
        damageListGoodsService.save(damageList, damageListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

}
