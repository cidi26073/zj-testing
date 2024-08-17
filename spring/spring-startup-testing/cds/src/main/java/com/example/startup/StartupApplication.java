package com.example.startup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class StartupApplication {

	private static final String FILENAME = "/tmp/test/cds.txt";

	private static Instant endTime;

	public static void main(String[] args) {
		var startTime = Instant.now();
		SpringApplication.run(StartupApplication.class);
		appendToFile(Duration.between(startTime, endTime));
		// exit failure to match restart policy
		System.exit(99);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		endTime = Instant.now();
	}

	public static void appendToFile(Duration duration) {
		try {
			Files.writeString(Paths.get(FILENAME), String.valueOf(duration.toMillis()) + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			// ignore this
		}
	}

}
