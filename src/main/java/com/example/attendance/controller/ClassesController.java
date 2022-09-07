package com.example.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendance.dto.response.Response;
import com.example.attendance.dto.response.ResponseModel;
import com.example.attendance.model.Classes;
import com.example.attendance.service.classes.ClassesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @GetMapping
    public ResponseEntity<ResponseModel> findAll() {
        List<Classes> classesList = (List<Classes>) classesService.findAll();
        if (classesList.isEmpty()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, classesList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody Classes classes) {
        if (classesService.existsByName(classes.getName())) {
            return new ResponseEntity<>(new ResponseModel(Response.NAME_IS_EXISTS, null), HttpStatus.CONFLICT);
        }
        classesService.save(classes);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, classes), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> findById(@PathVariable Long id) {
        Optional<Classes> classes = classesService.findById(id);
        if (!classes.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, classes.get()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable("id") Long id, @RequestBody Classes classes) {
        Optional<Classes> curClasses = classesService.findById(id);
        if (!curClasses.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        classes = classesService.update(curClasses, classes);
        classesService.save(classes);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, classes), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteById(@PathVariable("id") Long id) {
        Optional<Classes> classes = classesService.findById(id);
        if (!classes.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        classesService.delete(id);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, classes.get()), HttpStatus.OK);
    }
}
