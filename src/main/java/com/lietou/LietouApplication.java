package com.lietou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("com.lietou.mapper")
@SpringBootApplication
@EnableTransactionManagement // 事务
public class LietouApplication {
    public static void main(String[] args) {
        SpringApplication.run(LietouApplication.class, args);
    }
}
