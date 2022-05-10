package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.OverflowListGoodsService;
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
@RequestMapping("/overflowListGoods")
public class OverflowListGoodsController {

    @Autowired
    private OverflowListGoodsService overflowListGoodsService;

    /**
     * 保存商品报溢单
     *
     * @param overflowList         报溢清单
     * @param overflowListGoodsStr 报溢商品集合
     */
    @PostMapping("/save")
    public ServiceVO save(OverflowList overflowList, String overflowListGoodsStr, HttpSession session) {
        // 从session中获取user信息
        User user = (User) session.getAttribute("currentUser");
        overflowList.setUserId(user.getUserId());
        overflowListGoodsService.save(overflowList, overflowListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

}