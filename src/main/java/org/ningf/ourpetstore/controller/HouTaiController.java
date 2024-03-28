package org.ningf.ourpetstore.controller;

import org.ningf.ourpetstore.service.HouTaiService;
import org.ningf.ourpetstore.vo.UserVO;
import org.ningf.ourpetstore.vo.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: Lenovo
 * @time: 2024/3/22 8:45
 */

@Controller
@RequestMapping("/houTai")
public class HouTaiController {
    @Autowired
    private HouTaiService houTaiService;

    @GetMapping("loginForm")
    public String loginForm() {
        return "houTai/login";
    }

    @GetMapping("registerForm")
    public String registerForm() {
        return "houTai/register";
    }

    @GetMapping("userManage")
    public String userManage(Model model) {
        UsersVO users = houTaiService.getUsers();
        model.addAttribute("users", users);
        return "houTai/userManage";
    }

    @GetMapping("categoryManage")
    public String categoryManage() {
        return "houTai/categoryManage";
    }

    @GetMapping("orderManage")
    public String orderManage() {
        return "houTai/orderManage";
    }

    @GetMapping("productManage")
    public String productManage(){
        return "houTai/productManage";
    }

    @GetMapping("itemManage")
    public String itemManage(){
        return "houTai/itemManage";
    }

    @GetMapping("userDelete")
    public String userDelete(String userId, Model model) {
        UserVO  userVO = houTaiService.getUserById(userId);
        model.addAttribute("user", userVO);
        return "houTai/userDelete";
    }

    @GetMapping("userEdit")
    public String userEdit(String userId, Model model) {
        // 根据userId执行相应的逻辑，比如查询用户信息等
        UserVO  userVO = houTaiService.getUserById(userId);
        model.addAttribute("user", userVO);
        return "houTai/userEdit"; // 返回相应的视图名称
    }
}
