package com.springboot.service;

import com.springboot.pojo.SequenceIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceIdGeneratorService {
    @Autowired
    private MongoOperations mongo;

    public int getNextSequence(String seqName)
    {
        SequenceIdGenerator counter = mongo.findAndModify(
                Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq",1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                SequenceIdGenerator.class);
        return counter.getSeq();
    }
}
