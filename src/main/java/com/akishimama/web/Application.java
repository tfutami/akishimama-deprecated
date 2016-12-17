package com.akishimama.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }

    private static Class<Application> applicationClass = Application.class;
}