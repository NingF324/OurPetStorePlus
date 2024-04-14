package org.ningf.ourpetstore.vo;

import lombok.Data;

/**
 * @description:
 * @author: Lenovo
 * @time: 2024/4/14 21:16
 */
@Data
public class ProdVO {
    private String productId;
    private String categoryId;
    private String productName;
    private String description;
    private String image;
}
