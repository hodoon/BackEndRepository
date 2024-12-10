package com.example.spopiaproj.service;



import com.example.spopiaproj.dao.AdminUserDao;
import com.example.spopiaproj.model.UserDto;

import java.util.List;

public class AdminUserService {


    private final AdminUserDao adminUserDAO = new AdminUserDao();

    // 사용자 목록 조회
    public List<UserDto> getAllUsers() {
        return adminUserDAO.getAllUsers();
    }

    // 사용자 삭제
    public boolean deleteUser(String email) {
        return adminUserDAO.deleteUser(email);
    }

    // 사용자 추가
    public boolean addUser(UserDto user) {
        return adminUserDAO.addUser(user);
    }

    // 사용자 수정
    public boolean updateUser(UserDto user) {
        return adminUserDAO.editUser(user);
    }

    public UserDto getUserByEmail(String userEmail) {
        return adminUserDAO.getUserByEmail(userEmail);
    }
}
