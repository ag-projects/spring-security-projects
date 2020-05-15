package com.agharibi.springsecurity.controllers;

import com.agharibi.springsecurity.entities.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
        new Student(1, "James Bond"),
        new Student(2, "Maria Jones"),
        new Student(3, "Anna Peterson"));

    @GetMapping
    public List<Student> getStudents() {
        return STUDENTS;
    }

    @PostMapping
    public void register(@RequestBody Student student) {
        System.out.println(String.format("Creating a new student %s", student));
    }

    @DeleteMapping(path = "{studentId}")
    public void delete(@PathVariable("studentId") Integer studentId) {
        System.out.println(String.format("Deleting student : %d", studentId));
    }

    @PutMapping(path = "{studentId}")
    public void update(@PathVariable Integer studentId, @RequestBody Student student) {
        System.out.println(String.format("Updating student %s : %d ", student, studentId));
    }
}
