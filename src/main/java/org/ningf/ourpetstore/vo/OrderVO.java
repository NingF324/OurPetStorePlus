package org.ningf.ourpetstore.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderVO {
    private Integer orderId;
    private String userid;
    private Date orderDate;
    private String shipaddr1;
    private String shipaddr2;
    private String shipcity;
    private String shipstate;
    private String shipzip;
    private String shipcountry;
    private String billaddr1;
    private String billaddr2;
    private String billcity;
    private String billstate;
    private String billzip;
    private String billcountry;
    private String courier;
    private BigDecimal totalprice;
    private String billtofirstname;
    private String billtolastname;
    private String shiptofirstname;
    private String shiptolastname;
    private String creditcard;
    private String exprdate;
    private String cardtype;
    private String locale;
}
