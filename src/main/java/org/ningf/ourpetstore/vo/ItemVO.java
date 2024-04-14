package org.ningf.ourpetstore.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemVO {
    private String itemId;
    private String productId;
    private BigDecimal listPrice;
    private BigDecimal unitCost;
    private int supplierId;
    private String status;
    private String productName;
    private String descriptionImage;
    private String descriptionText;
    private String attributes;
    private Integer quantity;


}
