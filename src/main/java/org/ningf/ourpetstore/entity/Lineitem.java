package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName lineitem
 */
@TableName(value ="lineitem")
@Data
public class Lineitem implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer orderid;

    /**
     * 
     */
    private Integer linenum;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}