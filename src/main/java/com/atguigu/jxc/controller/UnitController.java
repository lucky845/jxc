package com.atguigu.jxc.controller;

import com.atguigu.jxc.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author lucky845
 * @since 2022年05月08日
 */
@RequestMapping("/unit")
@Controller
public class UnitController {

    @Autowired
    private UnitService unitService;

    /**
     * 查询所有的商品单位
     */
    @ResponseBody
    @PostMapping("/list")
    public Map<String, Object> list() {
        return unitService.list();
    }

}
