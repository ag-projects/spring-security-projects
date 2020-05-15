package com.agharibi.springsecurity.controllers;

import com.agharibi.springsecurity.entities.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student > STUDENTS = Arrays.asList(
        new Student(1, "James Bond"),
        new Student(2, "Maria Jones"),
        new Student(3, "Anna Peterson"));

    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENTS.stream()
            .filter(student -> student.getId().equals(studentId))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(String.format("Student %d does not exists", studentId)));
    }
}
