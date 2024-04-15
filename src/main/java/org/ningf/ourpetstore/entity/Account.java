package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName account
 */
@TableName(value ="account")
@Data
public class Account implements Serializable {
    /**
     * 
     */
    @TableId
    private String userid;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String firstname;

    /**
     * 
     */
    private String lastname;

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
    private String country;

    /**
     * 
     */
    private String phone;
    private String isavailable;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}