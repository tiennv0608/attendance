package com.example.attendance.controller;

import com.example.attendance.dto.response.Response;
import com.example.attendance.dto.response.ResponseModel;
import com.example.attendance.model.User;
import com.example.attendance.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<ResponseModel> findAll() {
        List<User> users = (List<User>) userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, users), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseModel> create(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(new ResponseModel(Response.USERNAME_IS_EXISTS, null), HttpStatus.CONFLICT);
        }
        String encode = passwordEncoder.encode(user.getPassword().trim());
        user.setPassword(encode);
        userService.save(user);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, user.get()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> currentUser = userService.findById(id);
        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(new ResponseModel(Response.USERNAME_IS_EXISTS, null), HttpStatus.CONFLICT);
        }
        user = userService.update(currentUser, user);
        userService.save(user);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteById(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(new ResponseModel(Response.SUCCESS, user.get()), HttpStatus.NO_CONTENT);
    }
}
