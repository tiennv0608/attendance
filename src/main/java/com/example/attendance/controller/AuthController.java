package com.example.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendance.dto.request.UserLoginForm;
import com.example.attendance.dto.response.JwtResponse;
import com.example.attendance.dto.response.Response;
import com.example.attendance.dto.response.ResponseModel;
import com.example.attendance.model.User;
import com.example.attendance.security.jwt.UserJwtService;
import com.example.attendance.security.principle.UserPrinciple;
import com.example.attendance.service.user.IUserService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserJwtService userJwtService;

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> login(@Validated @RequestBody UserLoginForm loginForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = userJwtService.generateTokenLogin(authentication);
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
            User currentUser = userService.findByUsername(loginForm.getUsername()).get();
            return new ResponseEntity<>(new ResponseModel(Response.SUCCESS,
                    new JwtResponse(currentUser.getId(), jwt, currentUser.getUsername(),
                            userPrinciple.getAuthorities())),
                    HttpStatus.OK);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ResponseModel(Response.OBJECT_NOT_FOUND, null), HttpStatus.FORBIDDEN);
        }
    }
}
