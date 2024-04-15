package org.ningf.ourpetstore.controller;

import org.ningf.ourpetstore.service.CatalogService;
import org.ningf.ourpetstore.service.HouTaiService;
import org.ningf.ourpetstore.vo.CategoryVO;
import org.ningf.ourpetstore.vo.ItemVO;
import org.ningf.ourpetstore.vo.ProductVO;
import org.ningf.ourpetstore.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Lenovo
 * @time: 2024/4/15 11:20
 */
@RestController
public class AxiosController {
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private HouTaiService houTaiService;
    @GetMapping("/houTai/checkCategory")
    public ResponseEntity<?> checkCategory(@RequestParam("id") String categoryId) {
        // 调用 CategoryService 中的方法来检查类别是否存在
        CategoryVO cat=catalogService.getCategory(categoryId);
        boolean categoryExists = cat != null;
        // 根据检查结果返回相应的数据
        if (categoryExists) {
            return ResponseEntity.ok().body("{\"exists\": true}");
        } else {
            return ResponseEntity.ok().body("{\"exists\": false}");
        }
    }

    @GetMapping("/houTai/checkItem")
    public ResponseEntity<?> checkItem(@RequestParam("itemId") String itemId) {
        ItemVO item = catalogService.getItem(itemId);
        boolean itemExists = item != null;
        // 根据检查结果返回相应的数据
        if (itemExists) {
            return ResponseEntity.ok().body("{\"exists\": true}");
        } else {
            return ResponseEntity.ok().body("{\"exists\": false}");
        }
    }

    @GetMapping("/houTai/checkProduct")
    public ResponseEntity<?> checkProduct(@RequestParam("productId") String productId) {
        ProductVO productVO=catalogService.getProduct(productId);
        boolean productExists = productVO != null;
        if(productExists){
            return ResponseEntity.ok().body("{\"exists\": true}");
        } else {
            return ResponseEntity.ok().body("{\"exists\": false}");
        }
    }

    @GetMapping("/houTai/checkUser")
    public ResponseEntity<?> checkUser(@RequestParam("userId") String userId) {
        // 在这里执行你的逻辑来检查userId是否已存在，假设userService是用来处理user相关逻辑的服务
        UserVO userVO = houTaiService.getUserById(userId);
        boolean userExists = userVO != null;
        if(userExists){
            return ResponseEntity.ok().body("{\"exists\": true}");
        } else {
            return ResponseEntity.ok().body("{\"exists\": false}");
        }
    }
}
