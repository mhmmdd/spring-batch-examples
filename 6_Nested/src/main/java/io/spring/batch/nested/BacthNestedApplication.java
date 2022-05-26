package io.spring.batch.nested;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class BacthNestedApplication {
    public static void main(String[] args) {
        SpringApplication.run(BacthNestedApplication.class, args);
    }
}
