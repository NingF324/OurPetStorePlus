package org.ningf.ourpetstore.vo;

import lombok.Data;
import org.ningf.ourpetstore.entity.Product;

import java.util.List;

@Data
public class CategoryVO {
    private String categoryId;
    private String categoryName;
    private List<Product> productList;
}
