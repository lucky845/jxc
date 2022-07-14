package com.lucky845.jxc.controller;

import com.lucky845.jxc.domain.ServiceVO;
import com.lucky845.jxc.domain.SuccessCode;
import com.lucky845.jxc.entity.OverflowList;
import com.lucky845.jxc.entity.User;
import com.lucky845.jxc.service.OverflowListGoodsService;
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
@RequestMapping("/overflowListGoods")
public class OverflowListGoodsController {

    @Resource
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

    /**
     * 报溢单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     */
    @PostMapping("/list")
    public Map<String, Object> list(String sTime, String eTime) {
        return overflowListGoodsService.list(sTime, eTime);
    }

    /**
     * 查询报溢单商品信息
     *
     * @param overflowListId 报溢单id
     */
    @PostMapping("/goodsList")
    public Map<String, Object> goodsList(Integer overflowListId) {
        return overflowListGoodsService.goodsList(overflowListId);
    }

}
