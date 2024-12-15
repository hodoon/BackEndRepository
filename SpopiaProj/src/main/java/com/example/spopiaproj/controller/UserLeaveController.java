package com.example.spopiaproj.controller;

import com.example.spopiaproj.HelloServlet;
import com.example.spopiaproj.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLeaveController extends HelloServlet {
    UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String userEmail = session.getAttribute("userEmail").toString();
        if (userEmail == null){
            System.out.println("login required");
            resp.sendRedirect(req.getContextPath() + "/");
        } else{
            userService.userLeave(userEmail);
            session.removeAttribute("isLogin");
            session.removeAttribute("userEmail");
            }
        resp.sendRedirect(req.getContextPath() + "/user/logout");
    }
}
