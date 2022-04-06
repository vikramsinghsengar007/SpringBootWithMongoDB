package com.springboot.response;

import com.springboot.pojo.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericResponse extends ResponseEntity<Student> {
    public GenericResponse(Student body, HttpStatus status) {
        super(body, status);
    }
}
