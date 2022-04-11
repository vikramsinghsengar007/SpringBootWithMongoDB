package com.springboot.service;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.exception.StudentException;
import com.springboot.pojo.Student;

import java.util.List;


public interface StudentService {

     List<Student> getStudent();

    Student createStudent(Student student) throws ResourceNotFoundException;

    List<Student> createListStudents(List<Student> studentList);

    Student getStudentById(int id) throws StudentException;

    Student deleteStudentById(int id) throws StudentException;

    Student updateStudent(Student student) throws StudentException;
}
