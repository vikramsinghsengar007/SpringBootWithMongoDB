package com.springboot.service.impl;

import com.springboot.pojo.Student;
import com.springboot.repository.StudentRepository;
import com.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> getStudent(){
        return repository.findAll();
    }
}
