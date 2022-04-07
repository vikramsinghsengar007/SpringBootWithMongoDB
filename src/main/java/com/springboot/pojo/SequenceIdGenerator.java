package com.springboot.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequenceIdGenerator")
@Data
public class SequenceIdGenerator {
    @Id
    private String id;
    private int seq;
}
