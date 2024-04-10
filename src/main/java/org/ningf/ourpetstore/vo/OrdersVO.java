package org.ningf.ourpetstore.vo;

import lombok.Data;
import org.ningf.ourpetstore.entity.Orders;

import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2024/4/10 8:47
 */
@Data
public class OrdersVO {
    private List<Orders> ordersList;
}
