package com.example.attendance.service.user;

import com.example.attendance.model.User;
import com.example.attendance.service.IGeneralService;

public interface IUserService extends IGeneralService<User> {

    Boolean existsByUsername(String username);

}
