package com.example.firestore.a;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ObjectARepository extends ReactiveCrudRepository<ObjectA, String> {

    Flux<ObjectA> findByFieldA1(String fieldA1);

    Flux<ObjectA> findAllByFieldA1(String fieldA1);

    Mono<ObjectA> findFirstByFieldA1(String fieldA1);

    Mono<ObjectA> findFirstByFieldA3FieldAA2(Integer fieldAA2);

    Mono<ObjectA> findFirstByFieldA3FieldAA4(BigDecimal fieldAA4);

}
