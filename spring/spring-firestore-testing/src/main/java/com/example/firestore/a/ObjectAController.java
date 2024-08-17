package com.example.firestore.a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.math.BigDecimal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ObjectAController {

    @Autowired
    private ObjectARepository repository;

    @PostMapping("/a/{id}")
    public Mono<ObjectA> putRandomFieldA(@PathVariable String id) {
        return repository
                .save(new ObjectA(id, "fieldA1", "fieldA2",
                        new ObjectA.ObjectAA("fieldA1", 1, new Date(), BigDecimal.ONE)));
    }

    @GetMapping("/a-s")
    public Flux<ObjectA> find(@RequestParam String fieldA1) {
        return repository.findByFieldA1(fieldA1);
    }

    @GetMapping("/a-all")
    public Flux<ObjectA> findAll(@RequestParam String fieldA1) {
        return repository.findAllByFieldA1(fieldA1);
    }

    @GetMapping("/a")
    public Mono<ObjectA> findFirst(@RequestParam String fieldA1) {
        return repository.findFirstByFieldA1(fieldA1);
    }

    @GetMapping("/a/a")
    public Mono<ObjectA> findFirst(@RequestParam(required = false) Integer fieldAA2,
            @RequestParam(required = false) BigDecimal fieldAA4) {
        return fieldAA2 != null ? repository.findFirstByFieldA3FieldAA2(fieldAA2)
                : repository.findFirstByFieldA3FieldAA4(fieldAA4);
    }

}
