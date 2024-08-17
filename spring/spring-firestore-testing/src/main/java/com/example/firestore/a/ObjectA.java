package com.example.firestore.a;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;
import com.google.cloud.spring.data.firestore.Document;

import java.util.Date;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IgnoreExtraProperties
@Document(collectionName = "object")
public class ObjectA {

    @DocumentId
    public String id;

    public String fieldA1;

    public String fieldA2;

    public ObjectAA fieldA3;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ObjectAA {

        public String fieldAA1;
    
        public Integer fieldAA2;
    
        public Date fieldAA3;
    
        public BigDecimal fieldAA4;

    }

}
