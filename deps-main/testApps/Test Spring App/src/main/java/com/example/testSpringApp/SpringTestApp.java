package com.example.testSpringApp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "Test API", version = "1.0.0")
)
@EnableFeignClients
@Slf4j
public class SpringTestApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringTestApp.class, args);
    }

}
