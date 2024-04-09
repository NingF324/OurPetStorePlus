package org.ningf.ourpetstore.controller;

import org.ningf.ourpetstore.service.CatalogService;
import org.ningf.ourpetstore.service.HouTaiService;
import org.ningf.ourpetstore.vo.*;
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

    @Autowired
    private CatalogService catalogService;

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

    @GetMapping("categoryEditForm")
    public String categoryEditForm(String categoryId, Model model) {
        CategoryVO categoryVO = catalogService.getCategory(categoryId);
        model.addAttribute("category", categoryVO);
        return "houTai/categoryEdit";
    }

    @GetMapping("categoryDeleteForm")
    public String categoryDeleteForm(String categoryId, Model model) {
        CategoryVO categoryVO = catalogService.getCategory(categoryId);
        model.addAttribute("category", categoryVO);
        return "houTai/categoryDelete";
    }

    @GetMapping("categoryNewForm")
    public String categoryNewForm() {
        return "houTai/categoryNew";
    }

    @GetMapping("orderManage")
    public String orderManage() {
        return "houTai/orderManage";
    }

    @GetMapping("productManage")
    public String productManage(String categoryId, Model model) {
        CategoryVO categoryVO = catalogService.getCategory(categoryId);
        model.addAttribute("category", categoryVO);
        return "houTai/productManage";
    }

    @GetMapping("productEditForm")
    public String productEditForm(String productId, Model model) {
        ProductVO productVO = catalogService.getProduct(productId);
        model.addAttribute("product", productVO);
        return "houTai/productEdit";
    }

    @GetMapping("productDeleteForm")
    public String productDeleteForm(String productId, Model model) {
        ProductVO productVO = catalogService.getProduct(productId);
        model.addAttribute("product", productVO);
        return "houTai/productDelete";
    }

    @GetMapping("productNewForm")
    public String productNewForm() {
        return "houTai/productNew";
    }

    @GetMapping("itemManage")
    public String itemManage(String productId, Model model) {
        ProductVO productVO = catalogService.getProduct(productId);
        model.addAttribute("product", productVO);
        return "houTai/itemManage";
    }

    @GetMapping("itemEditForm")
    public String itemEditForm(String itemId, Model model) {
        ItemVO itemVO = catalogService.getItem(itemId);
        model.addAttribute("item", itemVO);
        return "houTai/itemEdit"; // 返回相应的视图名称
    }

    @GetMapping("itemDeleteForm")
    public String itemDeleteForm(String itemId, Model model) {
        ItemVO itemVO = catalogService.getItem(itemId);
        model.addAttribute("item", itemVO);
        return "houTai/itemDelete";
    }

    @GetMapping("itemNewForm")
        public String itemNewForm() {
                return "houTai/itemNew";
        }


    @GetMapping("userDeleteForm")
    public String userDeleteForm(String userId, Model model) {
        UserVO userVO = houTaiService.getUserById(userId);
        model.addAttribute("user", userVO);
        return "houTai/userDelete";
    }

    @GetMapping("userEditForm")
    public String userEditForm(String userId, Model model) {
        // 根据userId执行相应的逻辑，比如查询用户信息等
        UserVO userVO = houTaiService.getUserById(userId);
        model.addAttribute("user", userVO);
        return "houTai/userEdit"; // 返回相应的视图名称
    }

    @GetMapping("userNewForm")
    public String userNewForm() {
        return "houTai/userNew";
    }

    @GetMapping("userDelete")
    public void userDelete(String userId) {
        houTaiService.deleteUser(userId);
    }

    @GetMapping("userEdit")
    public void userEdit(UserVO userVO) {
        houTaiService.updateUser(userVO);
    }

    @GetMapping("userCreate")
    public void userCreate(UserVO userVO) {
        houTaiService.createUser(userVO);
    }

}
