package com.example.BookStoreRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookStoreRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreRestApplication.class, args);
	}

}
