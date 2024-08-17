package com.example.firestore.b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class ObjectBController {

    @Autowired
    private ObjectBRepository repository;

    @PostMapping("/b/{id}")
    public Mono<ObjectB> putRandomFieldA(@PathVariable String id) {
        return repository.save(
                new ObjectB(id, "fieldB1", "fieldB2", new ObjectB.ObjectBA("fieldA1", 1, new Date(), -10.015151d)));
    }

}
