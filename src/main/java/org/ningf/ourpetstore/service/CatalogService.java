package org.ningf.ourpetstore.service;


import org.ningf.ourpetstore.entity.Category;
import org.ningf.ourpetstore.entity.Orders;
import org.ningf.ourpetstore.entity.Product;
import org.ningf.ourpetstore.vo.CategoryVO;
import org.ningf.ourpetstore.vo.ItemVO;
import org.ningf.ourpetstore.vo.ProductVO;

import java.util.List;

public interface CatalogService {

    public CategoryVO getCategory(String categoryId);
    public List<Category> getAllCategories();
    public List<Orders> getAllOrders();

    public ProductVO getProduct(String productId);

    public ItemVO getItem(String itemId);
    //category
    public boolean createCategory(CategoryVO categoryVO);
    public boolean deleteCategory(String categoryId);
    public boolean updateCategory(CategoryVO categoryVO);
    //product
    public boolean deleteProduct(ProductVO productVO);
    public boolean updateProduct(ProductVO productVO);
    public boolean createProduct(ProductVO productVO);
    //item
    public boolean deleteItem(ItemVO itemVO);
    public boolean updateItem(ItemVO itemVO);
    public boolean createItem(ItemVO itemVO);
}
