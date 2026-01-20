package com.requestnow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.requestnow")
public class RequestNowApplication {
    public static void main(String[] args) {
        SpringApplication.run(RequestNowApplication.class, args);
    }
}