package org.ningf.ourpetstore.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.ningf.ourpetstore.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    @Results({
            @Result(property = "categoryId", column = "catid"),
            @Result(property = "categoryName", column = "name"),
            @Result(property = "description",column = "descn")
            // 其他属性映射
    })
    @Select("SELECT * FROM category")
    List<Category> selectAllCategories();
}
