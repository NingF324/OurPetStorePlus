package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description:
 * @author: Lenovo
 * @time: 2024/3/19 14:31
 */

@Data
@TableName("category")
public class Category {
    @TableId("catid")
    private String categoryId;
    private String name;
    @TableField("descn")
    private String description;
}
