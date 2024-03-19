package org.ningf.ourpetstore.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.ningf.ourpetstore.entity.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMapper extends BaseMapper<Item> {
}
