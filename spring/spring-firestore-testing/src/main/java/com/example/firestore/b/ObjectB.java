package com.example.firestore.b;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;
import com.google.cloud.spring.data.firestore.Document;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IgnoreExtraProperties
@Document(collectionName = "object")
public class ObjectB {

    @DocumentId
    public String id;

    public String fieldB1;

    public String fieldB2;

    public ObjectBA fieldA3;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ObjectBA {

        public String fieldAA1;
    
        public Integer fieldAA2;
    
        public Date fieldAA3;
    
        public Double fieldAA4;

    }

}
