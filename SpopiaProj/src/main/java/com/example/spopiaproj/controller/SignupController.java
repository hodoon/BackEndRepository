package com.example.spopiaproj.controller;

import com.example.spopiaproj.model.UserDto;
import com.example.spopiaproj.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/signupProc")
public class SignupController extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if(!req.getParameterMap().isEmpty()){
            UserDto userDto = new UserDto();
            userDto.setUserEmail(req.getParameter("userEmail"));
            userDto.setUserPw(req.getParameter("userPw"));
            userDto.setUserNick(req.getParameter("userNick"));
            userDto.setUserName(req.getParameter("userName"));
            userDto.setUserGender(req.getParameter("userGender"));
            userDto.setUserDob(req.getParameter("userDob"));
            userDto.setUserContact(req.getParameter("userContact"));

            userService.signUpUser(userDto);
        } else {
            throw new RuntimeException("회원가입 정보를 입력해주세요.");
        }

        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/");
    }
}
