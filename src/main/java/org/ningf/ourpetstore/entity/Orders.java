package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName orders
 */
@TableName(value ="orders")
@Data
public class Orders implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer orderid;

    /**
     * 
     */
    private String userid;

    /**
     * 
     */
    private Date orderdate;

    /**
     * 
     */
    private String shipaddr1;

    /**
     * 
     */
    private String shipaddr2;

    /**
     * 
     */
    private String shipcity;

    /**
     * 
     */
    private String shipstate;

    /**
     * 
     */
    private String shipzip;

    /**
     * 
     */
    private String shipcountry;

    /**
     * 
     */
    private String billaddr1;

    /**
     * 
     */
    private String billaddr2;

    /**
     * 
     */
    private String billcity;

    /**
     * 
     */
    private String billstate;

    /**
     * 
     */
    private String billzip;

    /**
     * 
     */
    private String billcountry;

    /**
     * 
     */
    private String courier;

    /**
     * 
     */
    private BigDecimal totalprice;

    /**
     * 
     */
    private String billtofirstname;

    /**
     * 
     */
    private String billtolastname;

    /**
     * 
     */
    private String shiptofirstname;

    /**
     * 
     */
    private String shiptolastname;

    /**
     * 
     */
    private String creditcard;

    /**
     * 
     */
    private String exprdate;

    /**
     * 
     */
    private String cardtype;

    /**
     * 
     */
    private String locale;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}