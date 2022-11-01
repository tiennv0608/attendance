package com.example.attendance.controller;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendance.dto.response.Response;
import com.example.attendance.dto.response.ResponseModel;
import com.example.attendance.model.Teacher;
import com.example.attendance.service.teacher.TeacherService;

@RestController
@RequestMapping("/teachers")
@CrossOrigin("*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ResponseModel> findAll() {
        List<Teacher> teachers = (List<Teacher>) teacherService.findAll();
        if (teachers.size() == 0) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, teachers), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, teacher), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> findOne(@PathVariable("id") Long id) {
        Optional<Teacher> teacher = teacherService.findById(id);
        if (!teacher.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, teacher.get()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> update(@RequestBody Teacher teacher, @PathVariable("id") Long id) {
        Optional<Teacher> curTeacher = teacherService.findById(id);
        if (!curTeacher.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        teacher = teacherService.update(curTeacher, teacher);
        teacherService.save(teacher);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, teacher), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id) {
        Optional<Teacher> teacher = teacherService.findById(id);
        if (!teacher.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        teacherService.delete(id);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, teacher), HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<ResponseModel> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        List<Teacher> list = teacherService.getAllTeacher(pageNo, pageSize);
        if (list.size() == 0) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, list), HttpStatus.OK);
    }
}
