package org.ningf.ourpetstore.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ningf.ourpetstore.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper extends BaseMapper<Product> {
}
