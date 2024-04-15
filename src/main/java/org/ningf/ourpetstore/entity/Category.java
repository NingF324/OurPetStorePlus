package org.ningf.ourpetstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("category")
public class Category {
    @TableId(value = "catid")
    private String categoryId;
    @TableField("name")
    private String categoryName;
    @TableField("descn")
    private String description;
    private String isavailable;
}
