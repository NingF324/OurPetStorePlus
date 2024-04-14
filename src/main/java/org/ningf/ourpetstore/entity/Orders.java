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

    @TableId(value = "orderid")
    private Integer orderId;
    @TableField("userid")
    private String userId;
    @TableField("orderdate")
    private Date orderDate;
    @TableField("shipaddr1")
    private String shipAddr1;
    @TableField("shipaddr2")
    private String shipAddr2;
    @TableField("shipcity")
    private String shipCity;
    @TableField("shipstate")
    private String shipState;
    @TableField("shipzip")
    private String shipZip;
    @TableField("shipcountry")
    private String shipCountry;
    @TableField("billaddr1")
    private String billAddr1;
    @TableField("billaddr2")
    private String billAddr2;
    @TableField("billcity")
    private String billCity;
    @TableField("billstate")
    private String billState;
    @TableField("billzip")
    private String billZip;
    @TableField("billcountry")
    private String billCountry;
    private String courier;
    @TableField("totalprice")
    private BigDecimal totalPrice;
    @TableField("billtofirstname")
    private String billToFirstname;
    @TableField("billtolastname")
    private String billToLastname;
    @TableField("shiptofirstname")
    private String shipToFirstname;
    @TableField("shiptolastname")
    private String shipToLastname;
    @TableField("creditcard")
    private String creditCard;
    @TableField("exprdate")
    private String exprDate;
    @TableField("cardtype")
    private String cardType;
    private String locale;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}