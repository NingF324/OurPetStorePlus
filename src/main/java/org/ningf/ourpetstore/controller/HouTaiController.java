package org.ningf.ourpetstore.controller;

import org.ningf.ourpetstore.entity.Orders;
import org.ningf.ourpetstore.service.CatalogService;
import org.ningf.ourpetstore.service.HouTaiService;
import org.ningf.ourpetstore.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("login")
    public String login(@RequestParam("adminname") String adminname, @RequestParam("adminpassword") String adminpassword) {
        boolean login = houTaiService.login(adminname, adminpassword);
        if (login) {
            return "houTai/categoryManage";
        } else {
            return "houTai/login";
        }
    }

    @GetMapping("registerForm")
    public String registerForm() {
        return "houTai/register";
    }

    @PostMapping("register")
    public String register(@RequestParam("adminname") String adminname, @RequestParam("adminpassword") String adminpassword) {
        // 处理注册逻辑
        boolean login = houTaiService.register(adminname, adminpassword);
        if (login) {
            return "houTai/login";
        } else {
            return "houTai/register";
        }
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

    @PostMapping("categoryEdit")
    public String categoryEdit(@ModelAttribute("CategoryVO") CategoryVO categoryVO) {
        System.out.println("id:" + categoryVO.getCategoryId());
        houTaiService.categoryEdit(categoryVO);
        return "houTai/categoryManage";
    }

    @GetMapping("categoryDeleteForm")
    public String categoryDeleteForm(String categoryId, Model model) {
        CategoryVO categoryVO = catalogService.getCategory(categoryId);
        model.addAttribute("category", categoryVO);
        return "houTai/categoryDelete";
    }

    @PostMapping("categoryDelete")
    public String categoryDelete(String categoryId) {
        catalogService.deleteCategory(categoryId);
        return "houTai/categoryManage";
    }

    @GetMapping("categoryNewForm")
    public String categoryNewForm() {
        return "houTai/categoryNew";
    }

    @PostMapping("categoryCreate")
    public String categoryCreate(CategoryVO categoryVO) {
        catalogService.createCategory(categoryVO);
        return "houTai/categoryManage";
    }

    @GetMapping("orderManage")
    public String orderManage(Model model) {
        OrdersVO orders = houTaiService.getOrders();
        model.addAttribute("orders", orders);
        return "houTai/orderManage";
    }

    @GetMapping("orderEditForm")
    public String orderEditForm(String orderId, Model model) {
        Orders orders = houTaiService.getOrderById(orderId);
        model.addAttribute("order", orders);
        return "houTai/orderEdit";
    }

    @PostMapping("orderEdit")
    public String orderEdit(Orders orders) {
        houTaiService.updateOrder(orders);
        return "houTai/orderManage";
    }

    @GetMapping("orderDeleteForm")
    public String orderDeleteForm(String orderId, Model model) {
        Orders orders = houTaiService.getOrderById(orderId);
        model.addAttribute("order", orders);
        return "houTai/orderDelete";
    }

    @PostMapping("orderDelete")
    public String orderDelete(String orderId) {
        houTaiService.deleteOrder(orderId);
        return "houTai/orderManage";
    }

    @GetMapping("orderView")
    public String orderView(String orderId, Model model) {
        Orders orders = houTaiService.getOrderById(orderId);
        model.addAttribute("order", orders);
        return "houTai/orderView";
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

    @PostMapping("productEdit")
    public String productEdit(ProductVO productVO) {
        catalogService.updateProduct(productVO);
        return "houTai/productManage";
    }

    @GetMapping("productDeleteForm")
    public String productDeleteForm(String productId, Model model) {
        ProductVO productVO = catalogService.getProduct(productId);
        model.addAttribute("product", productVO);
        return "houTai/productDelete";
    }

    @PostMapping("productDelete")
    public String productDelete(String productId) {
        catalogService.deleteProduct(productId);
        return "houTai/productManage";
    }

    @GetMapping("productNewForm")
    public String productNewForm() {
        return "houTai/productNew";
    }

    @PostMapping("productCreate")
    public String productCreate(ProductVO productVO) {
        catalogService.createProduct(productVO);
        return "houTai/productManage";
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

    @PostMapping("itemEdit")
    public String itemEdit(ItemVO itemVO) {
        catalogService.updateItem(itemVO);
        return "houTai/itemManage";
    }

    @GetMapping("itemDeleteForm")
    public String itemDeleteForm(String itemId, Model model) {
        ItemVO itemVO = catalogService.getItem(itemId);
        model.addAttribute("item", itemVO);
        return "houTai/itemDelete";
    }

    @PostMapping("itemDelete")
    public String itemDelete(String itemId) {
        catalogService.deleteItem(itemId);
        return "houTai/itemManage";
    }

    @GetMapping("itemNewForm")
    public String itemNewForm() {
        return "houTai/itemNew";
    }

    @PostMapping("itemCreate")
    public String itemCreate(ItemVO itemVO) {
        catalogService.createItem(itemVO);
        return "houTai/itemManage";
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

    @PostMapping("userDelete")
    public void userDelete(String userId) {
        houTaiService.deleteUser(userId);
    }

    @PostMapping("userEdit")
    public void userEdit(UserVO userVO) {
        houTaiService.updateUser(userVO);
    }

    @PostMapping("userCreate")
    public void userCreate(UserVO userVO) {
        houTaiService.createUser(userVO);
    }

}
