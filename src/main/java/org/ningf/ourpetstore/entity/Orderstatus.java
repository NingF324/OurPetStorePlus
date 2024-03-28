package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName orderstatus
 */
@TableName(value ="orderstatus")
@Data
public class Orderstatus implements Serializable {
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
    private Date timestamp;

    /**
     * 
     */
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}