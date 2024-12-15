package com.example.spopiaproj.controller;

import com.example.spopiaproj.model.UserDto;
import com.example.spopiaproj.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/info-update-proc")
public class InfoUpdateController extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        UserDto userDto = new UserDto();
        userDto.setUserEmail(session.getAttribute("userEmail").toString());
        userDto.setUserName(req.getParameter("userName"));
        userDto.setUserNick(req.getParameter("userNick"));
        userDto.setUserDob(req.getParameter("userDob"));
        userDto.setUserContact(req.getParameter("userContact"));
        try {
            userService.updateUser(userDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            resp.sendRedirect(req.getContextPath() + "/user/my-page");
        }
    }
}
