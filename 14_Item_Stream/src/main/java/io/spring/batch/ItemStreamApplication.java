package io.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ItemStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemStreamApplication.class, args);
	}
}
