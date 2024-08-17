package com.example.mvc_virtual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class RemoteController {

    @Autowired
    private RestTemplate client;

    @GetMapping("/")
    public String ok() {
        return client.getForObject("http://openresty/echo", String.class);
    }

}
