package com.springboot.service;

import com.springboot.pojo.Student;
import com.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {


    public List<Student> getStudent();
}
