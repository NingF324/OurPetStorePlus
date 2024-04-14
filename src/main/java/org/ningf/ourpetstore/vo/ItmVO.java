package org.ningf.ourpetstore.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Lenovo
 * @time: 2024/4/14 21:57
 */
@Data

public class ItmVO {
private String itemId;
    private String productId;
    private BigDecimal listPrice;
    private BigDecimal unitCost;
    private int supplierId;
    private String status;
    private int inventory;
}
