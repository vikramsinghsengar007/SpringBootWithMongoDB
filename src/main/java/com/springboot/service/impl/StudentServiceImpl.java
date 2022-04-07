package com.springboot.service.impl;

import com.springboot.pojo.Student;
import com.springboot.repository.StudentRepository;
import com.springboot.service.SequenceIdGeneratorService;
import com.springboot.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Log4j2
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private SequenceIdGeneratorService idGeneratorService;

    @Override
    public List<Student> getStudent(){
        return repository.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        student.setSid(idGeneratorService.getNextSequence("sequenceIdGenerator"));
        return repository.save(student);
    }
    @Override
    public List<Student> createListStudents(List<Student> studentList) {
         studentList.stream()
                .forEach(student -> student.setSid(idGeneratorService.getNextSequence("sequenceIdGenerator")));
        return repository.saveAll(studentList);
    }

    @Override
    public Student getStudentById(int id) {
        return Optional.ofNullable(repository.findById(String.valueOf(id))).get().orElse(null);
    }
}
