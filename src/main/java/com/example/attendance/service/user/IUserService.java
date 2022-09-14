package com.example.attendance.service.user;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.attendance.model.User;
import com.example.attendance.service.IGeneralService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
