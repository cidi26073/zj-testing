package com.example.flux_virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class FluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(FluxApplication.class, args);
	}

	@Bean
	WebClient client() {
		return WebClient.builder()
				.baseUrl("http://openresty/echo")
				.build();
	}

	@Bean
	ReactorResourceFactory reactorClientResourceFactory() {
		System.setProperty("reactor.netty.ioWorkerCount", "5");
		return new ReactorResourceFactory();
	}

}
