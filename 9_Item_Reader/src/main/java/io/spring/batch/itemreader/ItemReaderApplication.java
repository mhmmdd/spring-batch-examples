package io.spring.batch.itemreader;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ItemReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemReaderApplication.class, args);
	}
}
