package com.lucky845.jxc.controller;

import com.lucky845.jxc.service.DrawImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description 生成图片验证码
 */
@Controller
public class DrawImageController {

    @Resource
    private DrawImageService drawImageService;

    /**
     * 生成图片验证码
     * @param request 请求
     * @param response 响应
     */
    @GetMapping("/drawImage")
    public void drawImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        drawImageService.drawImage(request, response);
    }

}
