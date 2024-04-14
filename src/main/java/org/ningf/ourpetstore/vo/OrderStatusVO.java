package org.ningf.ourpetstore.vo;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: Lenovo
 * @time: 2024/4/14 23:39
 */
@Data
public class OrderStatusVO {
    private String orderId;
    private String userId;
    private String status;
    private Date orderDate;
    private String trackingNumber;
}
