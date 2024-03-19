package org.ningf.ourpetstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.ningf.ourpetstore.persistence")
public class OurPetStoreSsmDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(OurPetStoreSsmDevApplication.class, args);
    }

}
