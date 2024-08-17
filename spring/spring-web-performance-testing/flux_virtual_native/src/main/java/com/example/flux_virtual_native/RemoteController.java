package com.example.flux_virtual_native;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class RemoteController {

    @Autowired
    private WebClient client;

    @GetMapping("/")
    public Mono<String> ok() {
        return client.get()
                .retrieve()
                .bodyToMono(String.class);
    }

}
