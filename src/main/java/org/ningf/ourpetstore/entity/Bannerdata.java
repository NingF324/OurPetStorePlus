package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName bannerdata
 */
@TableName(value ="bannerdata")
@Data
public class Bannerdata implements Serializable {
    /**
     * 
     */
    @TableId
    private String favcategory;

    /**
     * 
     */
    private String bannername;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}