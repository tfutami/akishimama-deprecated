package com.akishimama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.akishimama" })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }

    private static Class<Application> applicationClass = Application.class;
}