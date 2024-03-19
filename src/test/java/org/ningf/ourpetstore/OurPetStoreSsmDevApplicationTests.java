package org.ningf.ourpetstore;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.ningf.ourpetstore.persistence.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("org.ningf.ourpetstore.persistence")
class OurPetStoreSsmDevApplicationTests {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void contextLoads() {
        System.out.println(categoryMapper.selectById("FISH"));
    }

}
