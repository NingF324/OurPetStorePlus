package org.ningf.ourpetstore.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ningf.ourpetstore.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {
}
