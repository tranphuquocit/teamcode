package com.example.demo.database;

import org.aspectj.apache.bcel.util.Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.models.Product;

@Configuration
public class Database {

    @Bean
    CommandLineRunner initDatabase(Repository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product product1 = new Product(1, "Acer", "2022", 25000000.0, "Strong");
                Product productB = new Product(2, "Dell", "2021", 15000000.0, "Medium");
                System.out.println("insert data: "+productRepository.save(productA));
                System.out.println("insert data: "+productRepository.save(productB));
            }
        };
    }
}