package org.ningf.ourpetstore.service;


import org.ningf.ourpetstore.entity.Category;
import org.ningf.ourpetstore.entity.Orders;
import org.ningf.ourpetstore.entity.Product;
import org.ningf.ourpetstore.vo.*;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface CatalogService {

    public CategoryVO getCategory(String categoryId);
    public List<CateVO> getAllCategories();
    public List<Orders> getAllOrders();

    public ProductVO getProduct(String productId);

    public ItemVO getItem(String itemId);
    //category
    public boolean createCategory(CategoryVO categoryVO);
    public boolean deleteCategory(String categoryId);
    public boolean updateCategory(CateVO categoryVO);
    //product
    public boolean deleteProduct(ProductVO productVO);
    public boolean updateProduct(ProdVO productVO);
    public boolean createProduct(ProductVO productVO);
    //item
    public boolean deleteItem(ItemVO itemVO);
    public boolean updateItem(ItmVO itemVO);
    public boolean createItem(ItemVO itemVO);
    public ProductVO searchItems(String keyword);
}
