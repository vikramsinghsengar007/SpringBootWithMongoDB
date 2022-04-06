package com.springboot.controller;

import com.springboot.pojo.Student;
import com.springboot.response.GenericResponse;
import com.springboot.service.StudentService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

@Autowired
    private StudentService service;

@GetMapping("/getAllStudents")
    private List<Student> getStudent(){
    return service.getStudent();
}
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
