package com.example.firestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.cloud.spring.data.firestore.repository.config.EnableReactiveFirestoreRepositories;

@SpringBootApplication
@EnableReactiveFirestoreRepositories
public class FirestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirestoreApplication.class, args);
	}

}
