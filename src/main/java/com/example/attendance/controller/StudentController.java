package com.example.attendance.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.attendance.dto.response.Response;
import com.example.attendance.dto.response.ResponseModel;
import com.example.attendance.model.Student;
import com.example.attendance.service.student.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<ResponseModel> findAll() {
        List<Student> students = (List<Student>) studentService.findAll();
        if (students.isEmpty()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, students), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody Student student) {
        studentService.save(student);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, student), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id) {
        Optional<Student> student = studentService.findById(id);
        if (!student.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, student.get()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable("id") Long id, @RequestBody Student student) {
        Optional<Student> curStudent = studentService.findById(id);
        if (!curStudent.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        student = studentService.update(curStudent, student);
        studentService.save(student);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteById(@PathVariable("id") Long id) {
        Optional<Student> student = studentService.findById(id);
        if (!student.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        studentService.delete(id);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, student.get()), HttpStatus.NO_CONTENT);
    }

}
