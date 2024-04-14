package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName supplier
 */
@TableName(value ="supplier")
@Data
public class Supplier implements Serializable {
    /**
     * 
     */
    @TableId(value = "suppid")
    private Integer supplierId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private String addr1;

    /**
     * 
     */
    private String addr2;

    /**
     * 
     */
    private String city;

    /**
     * 
     */
    private String state;

    /**
     * 
     */
    private String zip;

    /**
     * 
     */
    private String phone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}