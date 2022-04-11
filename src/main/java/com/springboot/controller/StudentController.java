package com.springboot.controller;

import com.springboot.dto.StudentDTO;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.exception.StudentException;
import com.springboot.pojo.Student;
import com.springboot.response.GenericResponse;
import com.springboot.service.StudentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@Slf4j
public class StudentController {

    StudentService service;
    ModelMapper modelMapper;

    public StudentController(StudentService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot API Crud operation with MongoDB!";
    }

    @GetMapping("/getAllStudents")
    public List<StudentDTO> getAllStudents() {
        return service.getStudent().stream()
                .map(student -> modelMapper.map(student, StudentDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{sid}")
    public GenericResponse getStudentById(@PathVariable(value = "sid") int id) throws StudentException {
        Student student = service.getStudentById(id);
        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
        return new GenericResponse(studentDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public GenericResponse createStudent(@Valid @RequestBody StudentDTO studentDTO) throws ResourceNotFoundException {
        Student student = modelMapper.map(studentDTO, Student.class);
        Student student1 = service.createStudent(student);
        studentDTO = modelMapper.map(student1, StudentDTO.class);
        return new GenericResponse(studentDTO, HttpStatus.OK);
    }

    @PostMapping("/createListStudents")
    public GenericResponse createStudent(@Valid @RequestBody List<Student> studentList) {
        List<Student> listStudents = service.createListStudents(studentList);
        return new GenericResponse(
                listStudents.stream().map(studentDTO -> modelMapper.map(studentDTO, StudentDTO.class))
                        .collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteStudent(@Valid @PathVariable int id) throws StudentException {
        Student s = service.deleteStudentById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("student with id " + id + " deleted successfully...", Boolean.TRUE);
        response.put("Associated deleted data: " + s, Boolean.TRUE);
        return new GenericResponse(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public GenericResponse updateStudent(@Valid @RequestBody StudentDTO studentDTO) throws StudentException {
        Student s = modelMapper.map(studentDTO, Student.class);
        Student student1 = service.updateStudent(s);
        StudentDTO studentDTOs = modelMapper.map(student1, StudentDTO.class);
        return new GenericResponse(studentDTOs, HttpStatus.OK);
    }
}
