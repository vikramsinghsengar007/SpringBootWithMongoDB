package com.springboot.service;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.pojo.SequenceIdGenerator;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceIdGeneratorService {
    private final MongoOperations mongo;

    public SequenceIdGeneratorService(MongoOperations mongo){
        this.mongo = mongo;
    }

    public int getNextSequence(String seqName) throws ResourceNotFoundException {

        SequenceIdGenerator counter = mongo.findAndModify(
                Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq", 1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                SequenceIdGenerator.class);
        if (counter != null) {
            return counter.getSeq();
        } else {
            throw new ResourceNotFoundException("problem with SequenceIdGenerator...");
        }
    }
}
