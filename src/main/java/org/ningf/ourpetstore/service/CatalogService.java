package org.ningf.ourpetstore.service;


import org.ningf.ourpetstore.vo.CategoryVO;
import org.ningf.ourpetstore.vo.ItemVO;
import org.ningf.ourpetstore.vo.ProductVO;

public interface CatalogService {

    public CategoryVO getCategory(String categoryId);

    public ProductVO getProduct(String productId);

    public ItemVO getItem(String itemId);
}
