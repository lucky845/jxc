package com.lucky845.jxc.controller;

import com.lucky845.jxc.domain.ServiceVO;
import com.lucky845.jxc.domain.SuccessCode;
import com.lucky845.jxc.entity.DamageList;
import com.lucky845.jxc.entity.User;
import com.lucky845.jxc.service.DamageListGoodsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author lucky845
 * @since 2022年05月10日
 */
@RestController
@RequestMapping("/damageListGoods")
public class DamageListGoodsController {

    @Resource
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

    /**
     * 报损单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     */
    @PostMapping("/list")
    public Map<String, Object> list(String sTime, String eTime) {
        return damageListGoodsService.list(sTime, eTime);
    }

    /**
     * 查询报损单商品信息
     *
     * @param damageListId 报损单id
     */
    @PostMapping("/goodsList")
    public Map<String, Object> goodsList(Integer damageListId) {
        return damageListGoodsService.goodsList(damageListId);
    }

}
