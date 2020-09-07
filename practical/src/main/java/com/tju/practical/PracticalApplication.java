package com.tju.practical;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tju.practical")
@MapperScan("com.tju.practical.dao")
public class PracticalApplication {

    public static void main(String[] args) { SpringApplication.run(PracticalApplication.class, args); }

}
