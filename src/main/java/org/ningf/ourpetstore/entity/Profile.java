package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName profile
 */
@TableName(value ="profile")
@Data
public class Profile implements Serializable {
    /**
     * 
     */
    @TableId
    private String userid;

    /**
     * 
     */
    private String langpref;

    /**
     * 
     */
    private String favcategory;

    /**
     * 
     */
    private Integer mylistopt;

    /**
     * 
     */
    private Integer banneropt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}