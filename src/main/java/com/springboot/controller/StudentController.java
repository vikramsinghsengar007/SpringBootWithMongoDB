package com.springboot.controller;

import com.springboot.pojo.Student;
import com.springboot.response.GenericResponse;
import com.springboot.service.StudentService;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

@Autowired
    private StudentService service;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot API Crud operation with MongoDB!";
    }

@GetMapping("/getAllStudents")
    private List<Student> getAllStudents(){
    return service.getStudent();
}

    @GetMapping("/getStudentById/{id}")
    private Student getStudentById( @Valid @PathVariable int id){
        return service.getStudentById(id);
    }

@PostMapping("/create")
    private Student createStudent(@Valid @RequestBody  Student student){
       return service.createStudent(student);
}
    @PostMapping("/createListStudents")
    private List<Student> createStudent(@Valid @RequestBody  List<Student> studentList){
        return service.createListStudents(studentList);
    }
}
