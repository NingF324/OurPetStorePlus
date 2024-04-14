package org.ningf.ourpetstore.vo;

import lombok.Data;
import org.ningf.ourpetstore.entity.Category;
import org.ningf.ourpetstore.entity.Product;

import java.util.List;

@Data
public class CategoryVO {
    private String categoryId;
    private String categoryName;
    private String description;
    private List<Product> productList;
    private List<Category> categoryList;
}
