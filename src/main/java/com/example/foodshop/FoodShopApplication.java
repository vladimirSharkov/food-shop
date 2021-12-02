package com.example.foodshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FoodShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodShopApplication.class, args);
    }

}
