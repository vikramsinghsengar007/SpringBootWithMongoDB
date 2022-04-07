package com.springboot.service;

import com.springboot.pojo.Student;

import java.util.List;


public interface StudentService {


     List<Student> getStudent();

    Student createStudent(Student student);

    List<Student> createListStudents(List<Student> studentList);

    Student getStudentById(int id);
}
