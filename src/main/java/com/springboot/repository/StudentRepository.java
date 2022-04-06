package com.springboot.repository;

import com.springboot.pojo.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface  StudentRepository extends MongoRepository<Student, String> {
}
