package com.bytebuf.springbootelasticjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bytebuf.springbootelasticjob.dao")
public class SpringBootElasticJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootElasticJobApplication.class, args);
    }

}
