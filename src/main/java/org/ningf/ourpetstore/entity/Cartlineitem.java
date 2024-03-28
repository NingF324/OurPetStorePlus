package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @TableName cartlineitem
 */
@TableName(value ="cartlineitem")
@Data
public class Cartlineitem implements Serializable {

    /**
     * 
     */
    private String userid;

    /**
     * 
     */
    private String itemid;

    /**
     * 
     */
    private Integer quantity;

    /**
     * 
     */
    private BigDecimal unitprice;

    /**
     * 
     */
    private String productid;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private BigDecimal listprice;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}