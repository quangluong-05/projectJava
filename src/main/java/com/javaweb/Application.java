package com.javaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.javaweb", "controlleradvice", "Model", "service", "repository", "convertor", "config"})
@EntityScan(basePackages = {"repository.Entity"})  // Quét các class @Entity
@EnableJpaRepositories(basePackages = "repository")  // Quét các repository
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
