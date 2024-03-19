package org.ningf.ourpetstore.vo;

import lombok.Data;
import org.ningf.ourpetstore.entity.Item;

import java.util.List;

@Data
public class ProductVO {
    private String productId;
    private String categoryId;
    private String productName;

    private List<Item> itemList;
}
