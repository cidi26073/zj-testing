package com.example.firestore.b;

import org.springframework.stereotype.Repository;

import com.example.firestore.UpdateFirestoreReactiveRepository;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.spring.data.firestore.FirestoreTemplate;

@Repository
public class ObjectBRepository extends UpdateFirestoreReactiveRepository<ObjectB> {

    public ObjectBRepository(Firestore firestore, FirestoreTemplate template) {
        super(firestore, template, ObjectB.class);
    }

}
