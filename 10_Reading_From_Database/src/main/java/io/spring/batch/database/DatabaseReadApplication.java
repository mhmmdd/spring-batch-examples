package io.spring.batch.database;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class DatabaseReadApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatabaseReadApplication.class, args);
    }
}
