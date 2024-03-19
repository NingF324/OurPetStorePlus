package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Product {
    @TableId(value = "productid")
    private String productId; // product_id
    @TableField("category")
    private String categoryId;
    private String name;
    @TableField("descn")
    private String description;
}
