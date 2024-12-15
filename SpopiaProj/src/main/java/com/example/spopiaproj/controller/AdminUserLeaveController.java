package com.example.spopiaproj.controller;

import com.example.spopiaproj.HelloServlet;
import com.example.spopiaproj.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/user-leave")
public class AdminUserLeaveController extends HelloServlet {
    UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String userEmail = req.getParameter("userEmail");
        if (userEmail == null){
            System.out.println("User Information Required");
            resp.sendRedirect(req.getContextPath() + "/admin-page");
        } else{
            userService.userLeave(userEmail);
            resp.sendRedirect(req.getContextPath() + "/admin-page");
        }
    }
}
