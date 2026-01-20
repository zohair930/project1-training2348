package com.revature.erms_project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:test.properties")
@SpringBootApplication
public class ErmsProject1Application {

    public static void main(String[] args) {
        SpringApplication.run(ErmsProject1Application.class, args);
    }
}

