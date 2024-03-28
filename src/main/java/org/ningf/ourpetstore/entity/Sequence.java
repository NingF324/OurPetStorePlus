package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sequence
 */
@TableName(value ="sequence")
@Data
public class Sequence implements Serializable {
    /**
     * 
     */
    @TableId
    private String name;

    /**
     * 
     */
    private Integer nextid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}