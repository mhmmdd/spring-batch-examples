package io.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class BatchlisternerApplication {
	public static void main(String[] args) {
		SpringApplication.run(BatchlisternerApplication.class, args);
	}
}
