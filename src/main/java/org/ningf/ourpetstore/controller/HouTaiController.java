package org.ningf.ourpetstore.controller;

import org.ningf.ourpetstore.entity.Orders;
import org.ningf.ourpetstore.entity.Orderstatus;
import org.ningf.ourpetstore.persistence.ItemQuantityMapper;
import org.ningf.ourpetstore.persistence.OrderstatusMapper;
import org.ningf.ourpetstore.persistence.ProductMapper;
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
    @Autowired
    private ItemQuantityMapper itemQuantityMapper;
    @Autowired
    private OrderstatusMapper orderstatusMapper;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("loginForm")
    public String loginForm() {
        return "houTai/login";
    }

    @PostMapping("login")
    public String login(@RequestParam("adminname") String adminname, @RequestParam("adminpassword") String adminpassword,Model model) {
        boolean login = houTaiService.login(adminname, adminpassword);
        System.out.println(login);
        if (login) {
            return this.categoryManage(model);
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
    public String categoryManage(Model model) {
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setCategoryList(catalogService.getAllCategories());
        model.addAttribute("category", categoryVO);
        return "houTai/categoryManage";
    }

    @GetMapping("categoryEditForm")
    public String categoryEditForm(String categoryId, Model model) {
        CategoryVO categoryVO = catalogService.getCategory(categoryId);
        CateVO cateVO= new CateVO();
        cateVO.setCategoryId(categoryVO.getCategoryId());
        cateVO.setCategoryName(categoryVO.getCategoryName());
        String [] temp = categoryVO.getDescription().split("\"");
        String [] temp1 = temp[6].split("</font>");
        cateVO.setDescription(temp1[0].substring(1));
        cateVO.setImage(temp[1]);
        model.addAttribute("category", cateVO);
        return "houTai/categoryEdit";
    }

    @PostMapping("categoryEdit")
    public String categoryEdit(CateVO categoryVO) {
        boolean updateSuccessful = catalogService.updateCategory(categoryVO);
        if (updateSuccessful) {
            return "redirect:/houTai/categoryManage" ;
        } else {
            // 如果更新失败，重定向到编辑表单页面
            String updatedCategoryId = categoryVO.getCategoryId();
            return "redirect:/houTai/categoryEditForm?categoryId="+updatedCategoryId;
        }
    }

    @GetMapping("categoryDeleteForm")
    public String categoryDeleteForm(String categoryId, Model model) {
        CategoryVO categoryVO = catalogService.getCategory(categoryId);
        CateVO cateVO= new CateVO();
        cateVO.setCategoryId(categoryVO.getCategoryId());
        cateVO.setCategoryName(categoryVO.getCategoryName());
        String [] temp = categoryVO.getDescription().split("\"");
        String [] temp1 = temp[6].split("</font>");
        cateVO.setDescription(temp1[0].substring(1));
        cateVO.setImage(temp[1]);
        model.addAttribute("category", cateVO);
        return "houTai/categoryDelete";
    }

    @PostMapping("categoryDelete")
    public String categoryDelete(CategoryVO categoryVO) {
        boolean b = catalogService.deleteCategory(categoryVO.getCategoryId());
        if(b){
            return "redirect:/houTai/categoryManage";
        }else{
            String deletedCategoryId =  categoryVO.getCategoryId();
            return "redirect:/houTai/categoryDeleteForm?categoryId="+deletedCategoryId;
        }
    }

    @GetMapping("categoryNewForm")
    public String categoryNewForm() {
        return "houTai/categoryNew";
    }

    @PostMapping("categoryCreate")
    public String categoryCreate(CategoryVO categoryVO) {
        boolean b = catalogService.createCategory(categoryVO);
        if(b){
            return "redirect:/houTai/categoryManage";
        }else{

            return "redirect:/houTai/categoryNewForm";
        }
    }

    @GetMapping("orderManage")
    public String orderManage(Model model) {
        OrdersVO orders = new OrdersVO();
        orders.setOrdersList(catalogService.getAllOrders());
        model.addAttribute("orders", orders);
        return "houTai/orderManage";
    }
    @PostMapping("orderSearch")
    public String orderSearch(String keyword, Model model) {
        OrdersVO orders = new OrdersVO();
        orders.setOrdersList(houTaiService.searchOrders(keyword));
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
    public String orderEdit(OrderVO orders) {
        boolean updateSuccessful = houTaiService.updateOrder(orders);
        if (updateSuccessful) {
            return "redirect:/houTai/orderManage" ;
        } else {
            // 如果更新失败，重定向到编辑表单页面
            int updatedOrderId = orders.getOrderId();
            return "redirect:/houTai/orderEditForm?orderId="+updatedOrderId;
        }
    }

    @GetMapping("orderDeleteForm")
    public String orderDeleteForm(String orderId, Model model) {
        Orders orders = houTaiService.getOrderById(orderId);
        model.addAttribute("order", orders);
        return "houTai/orderDelete";
    }

    @PostMapping("orderDelete")
    public String orderDelete(String orderId) {
        boolean b = houTaiService.deleteOrder(orderId);
        if(b){
            return "redirect:/houTai/orderManage";
        }else{
            return "redirect:/houTai/productDeleteForm?productId="+orderId;
        }
    }

    @GetMapping("orderView")
    public String orderView(String orderId, Model model) {
        Orders orders = houTaiService.getOrderById(orderId);
        String status = houTaiService.getOrderStatusById(orderId).getStatus();
        String trackingNumber = houTaiService.getOrderStatusById(orderId).getTrackingnumber()+"";
        model.addAttribute("order", orders);
        model.addAttribute("status", status);
        model.addAttribute("trackingNumber", trackingNumber);
        return "houTai/orderView";
    }

    @GetMapping("orderDeliveryForm")
    public String orderDeliveryForm(String orderId ,Model model) {
        Orders orders = houTaiService.getOrderById(orderId);
        Orderstatus orderstatus=houTaiService.getOrderStatusById(orderId);
        OrderStatusVO orderStatusVO = new OrderStatusVO();
        orderStatusVO.setOrderId(orderId);
        orderStatusVO.setUserId(orders.getUserId());
        orderStatusVO.setOrderDate(orders.getOrderDate());
        orderStatusVO.setStatus(orderstatus.getStatus());
        orderStatusVO.setTrackingNumber(null);
        model.addAttribute("order", orderStatusVO);
        return "houTai/orderDelivery";
    }

    @PostMapping("orderDelivery")
    public String orderDelivery(OrderStatusVO orderStatusVO) {
        Orderstatus orderstatus = new Orderstatus();
        orderstatus.setOrderid(Integer.parseInt(orderStatusVO.getOrderId()));
        orderstatus.setLinenum(Integer.parseInt(orderStatusVO.getOrderId()));
        orderstatus.setTimestamp(orderStatusVO.getOrderDate());
        orderstatus.setStatus("S");
        orderstatus.setTrackingnumber(Integer.parseInt(orderStatusVO.getTrackingNumber()));
        int updateResult = orderstatusMapper.updateById(orderstatus);
        return updateResult > 0 ? "redirect:/houTai/orderManage" : "redirect:/houTai/orderDeliveryForm?orderId="+orderStatusVO.getOrderId();
    }

    @GetMapping("orderConfirm")
    public String orderConfirm(String orderId) {
        Orderstatus orderstatus = orderstatusMapper.selectById(orderId);
        orderstatus.setStatus("C");
        int updateResult = orderstatusMapper.updateById(orderstatus);
        return updateResult > 0 ? "redirect:/houTai/orderManage" : "redirect:/houTai/orderView?orderId="+orderId;
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
        ProdVO prodVO = new ProdVO();
        prodVO.setProductId(productVO.getProductId());
        prodVO.setCategoryId(productVO.getCategoryId());
        prodVO.setProductName(productVO.getProductName());
        String [] temp = productVO.getDescription().split("\"");
        prodVO.setDescription(temp[2].substring(1));
        prodVO.setImage(temp[1]);
        model.addAttribute("product", prodVO);
        return "houTai/productEdit";
    }

    @PostMapping("productEdit")
    public String productEdit(ProdVO productVO) {
        boolean updateSuccessful = catalogService.updateProduct(productVO);
        if (updateSuccessful) {
            // 如果更新成功，获取更新后的项目ID
            String updatedCategoryId = productVO.getCategoryId();
            // 重定向到前一个页面，并携带更新后的项目ID作为参数
            return "redirect:/houTai/productManage?categoryId=" + updatedCategoryId;
        } else {
            // 如果更新失败，重定向到编辑表单页面
            String updatedProductId = productVO.getProductId();
            return "redirect:/houTai/productEditForm?productId="+updatedProductId;
        }
    }

    @GetMapping("productDeleteForm")
    public String productDeleteForm(String productId, Model model) {
        ProductVO productVO = catalogService.getProduct(productId);
        model.addAttribute("product", productVO);
        return "houTai/productDelete";
    }

    @PostMapping("productDelete")
    public String productDelete(ProductVO productVO) {
        boolean b = catalogService.deleteProduct(productVO);
        if(b){
            String deletedCategoryId =  productVO.getCategoryId();
            return "redirect:/houTai/productManage?categoryId="+deletedCategoryId;
        }else{
            String deletedProductId = productVO.getProductId();
            return "redirect:/houTai/productDeleteForm?productId="+deletedProductId;
        }
    }

    @GetMapping("productNewForm")
    public String productNewForm() {
        return "houTai/productNew";
    }

    @PostMapping("productCreate")
    public String productCreate(ProductVO productVO) {
        boolean b = catalogService.createProduct(productVO);
        if(b){
            String updatedCategoryId =  productVO.getCategoryId();
            return "redirect:/houTai/productManage?categoryId="+updatedCategoryId;
        }else{
            return "redirect:/houTai/productNewForm";
        }
    }

    @GetMapping("itemManage")
    public String itemManage(String productId, Model model) {
        ProductVO productVO = catalogService.getProduct(productId);
        model.addAttribute("product", productVO);
        return "houTai/itemManage";
    }

    @GetMapping("itemEditForm")
    public String itemEditForm(String itemId, Model model) {
        ItemVO item = catalogService.getItem(itemId);
        ItmVO itmVO = new ItmVO();
        itmVO.setItemId(item.getItemId());
        itmVO.setProductId(item.getProductId());
        itmVO.setListPrice(item.getListPrice());
        itmVO.setUnitCost(item.getUnitCost());
        itmVO.setSupplierId(item.getSupplierId());
        itmVO.setStatus(item.getStatus());
        itmVO.setInventory(itemQuantityMapper.selectById(item.getItemId()).getQuantity());
        model.addAttribute("item", itmVO);
        return "houTai/itemEdit"; // 返回相应的视图名称
    }

    @PostMapping("itemEdit")
    public String itemEdit(ItmVO itemVO) {
        boolean updateSuccessful = catalogService.updateItem(itemVO);
        if (updateSuccessful) {
            // 如果更新成功，获取更新后的项目ID
            String updatedProductId = itemVO.getProductId();
            // 重定向到前一个页面，并携带更新后的项目ID作为参数
            return "redirect:/houTai/itemManage?productId=" + updatedProductId;
        } else {
            // 如果更新失败，重定向到编辑表单页面
            String updateItemId = itemVO.getItemId();
            return "redirect:/houTai/itemEditForm?itemId="+updateItemId;
        }
    }


    @GetMapping("itemDeleteForm")
    public String itemDeleteForm(String itemId, Model model) {
        ItemVO itemVO = catalogService.getItem(itemId);
        model.addAttribute("item", itemVO);
        return "houTai/itemDelete";
    }

    @PostMapping("itemDelete")
    public String itemDelete(ItemVO itemVO) {
        boolean b = catalogService.deleteItem(itemVO);
        if(b){
            String deletedProductId = itemVO.getProductId();
            return "redirect:/houTai/itemManage?productId="+deletedProductId;
        }else{
            String deletedItemId = itemVO.getItemId();
            return "redirect:/houTai/itemDeleteForm?itemId="+deletedItemId;
        }
    }

    @GetMapping("itemLaunch")
    public String itemLaunch(String itemId) {
        boolean b = houTaiService.launchItem(itemId);
        if(b){
            ItemVO item = catalogService.getItem(itemId);
            String updatedProductId =  item.getProductId();
            return "redirect:/houTai/itemManage?productId="+updatedProductId;
        }else{
            ItemVO item = catalogService.getItem(itemId);
            String updatedProductId =  item.getProductId();
            return "redirect:/houTai/itemManage?message=duplicateLaunch&productId="+updatedProductId;
        }
    }

    @GetMapping("itemDelist")
    public String itemDelist(String itemId) {
        boolean b = houTaiService.delistItem(itemId);
        if(b){
            ItemVO item = catalogService.getItem(itemId);
            String updatedProductId =  item.getProductId();
            return "redirect:/houTai/itemManage?productId="+updatedProductId;
        }else{
            ItemVO item = catalogService.getItem(itemId);
            String updatedProductId =  item.getProductId();
            return "redirect:/houTai/itemManage?message=duplicateDelist&productId="+updatedProductId;
        }
    }

    @GetMapping("itemNewForm")
    public String itemNewForm(Model model,String productId) {
        model.addAttribute("productId",productId);
        return "houTai/itemNew";
    }

    @PostMapping("itemCreate")
    public String itemCreate(ItemVO itemVO) {
        boolean b = catalogService.createItem(itemVO);
        if(b){
            String updatedProductId =  itemVO.getProductId();
            return "redirect:/houTai/itemManage?productId="+updatedProductId;
        }else{
            return "redirect:/houTai/itemNewForm";
        }
    }

    @PostMapping("itemSearch")
    public String itemSearch(String keyword, Model model) {
        ProductVO productVO = catalogService.searchItems(keyword);
        model.addAttribute("product", productVO);
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
    public String userDelete(String userId) {
        boolean b = houTaiService.deleteUser(userId);
        if(b){
            return "redirect:/houTai/userManage";
        }else{
            return "redirect:/houTai/userDeleteForm";
        }
    }

    @PostMapping("userEdit")
    public String userEdit(UserVO userVO) {
        boolean b = houTaiService.updateUser(userVO);
        if(b){
            return "redirect:/houTai/userManage";
        }else{
            return "redirect:/houTai/userEditForm";
        }
    }

    @PostMapping("userCreate")
    public String userCreate(UserVO userVO) {
        boolean b = houTaiService.createUser(userVO);
        if(b){
            return "redirect:/houTai/userManage";
        }else{
            return "redirect:/houTai/userNewForm";
        }
    }

}
