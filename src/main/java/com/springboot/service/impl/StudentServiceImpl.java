package com.springboot.service.impl;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.exception.StudentException;
import com.springboot.pojo.Student;
import com.springboot.repository.StudentRepository;
import com.springboot.service.SequenceIdGeneratorService;
import com.springboot.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    
    public StudentServiceImpl(StudentRepository repository, MongoTemplate template, SequenceIdGeneratorService idGeneratorService){

        this.repository = repository;
        this.template = template;
        this.idGeneratorService = idGeneratorService;
    }
    
    private final StudentRepository repository;

    private final MongoTemplate template;

    private final SequenceIdGeneratorService idGeneratorService;

    public List<Student> getStudent(){
        return repository.findAll();
    }

    @Override
    public Student createStudent(Student student) throws ResourceNotFoundException {
        student.setSid(idGeneratorService.getNextSequence("sequenceIdGenerator"));
        return repository.save(student);
    }
    @Override
    public List<Student> createListStudents(List<Student> studentList) {

         studentList
                .forEach(student -> {
                    try {
                        student.setSid(idGeneratorService.getNextSequence("sequenceIdGenerator"));
                    } catch (ResourceNotFoundException e) {
                        log.error("problem with sequenceIdGenerator ..");
                        e.printStackTrace();
                    }
                });
        return repository.saveAll(studentList);
    }

    @Override
    public Student getStudentById(int id) throws StudentException {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
       List<Student> student = template.find(query, Student.class);
        if(!student.isEmpty()) {
           return student.get(0);
        }else{
            log.error("student with id:{} not found ", id );
            throw new StudentException("student with id: "+ id +" not found ");
        }
    }

    @Override
    public Student deleteStudentById(int id) throws StudentException {
        final Student student = getStudentById(id);
        Optional<Student> s = Optional.ofNullable(student).isPresent() ? Optional.of(student) : Optional.empty();
        if(s.isPresent()) {
            repository.delete(student);
        }else{
            log.error("student with id:{} not found ", id );
            throw new StudentException("student with id: "+ id +" not found ");
        }
        return student;
    }

    @Override
    public Student updateStudent( Student student) throws StudentException {
        final Student s = getStudentById(student.getSid());
            s.setSname(student.getSname());
            return repository.save(s);
    }
}
