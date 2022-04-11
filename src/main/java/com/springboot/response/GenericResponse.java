package com.springboot.response;

import com.springboot.dto.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class GenericResponse extends ResponseEntity<Object> {
    public GenericResponse(StudentDTO body, HttpStatus status) {
        super(body, status);
    }
    public GenericResponse(List<StudentDTO> body, HttpStatus status) {
        super(body, status);
    }

    public GenericResponse(Map<String, Boolean> response, HttpStatus status) {
        super(response, status);
    }
}
