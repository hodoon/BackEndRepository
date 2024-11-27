package com.example.spopiaproj.service;

import com.example.spopiaproj.dao.UserDao;
import com.example.spopiaproj.model.UserDto;

public class UserService {
    private final UserDao userDao = new UserDao();

    public boolean registerUser(UserDto user){
        System.out.println("[DEBUG] Registering user: " + user.getEmail());
        return userDao.saveUser(user);
    }
}
