package org.ningf.ourpetstore.persistence;

import org.ningf.ourpetstore.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Lenovo
 * @description 针对表【admin】的数据库操作Mapper
 * @createDate 2024-04-10 10:35:25
 * @Entity org.ningf.ourpetstore.entity.Admin
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

}
