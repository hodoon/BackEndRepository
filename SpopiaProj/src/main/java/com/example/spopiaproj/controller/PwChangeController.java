package com.example.spopiaproj.controller;

import com.example.spopiaproj.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/pw-change-proc")
public class PwChangeController extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String userEmail = session.getAttribute("userEmail").toString();
        String currentPw = req.getParameter("currentPw");
        String newPw = req.getParameter("newPw");

        if (userEmail == null){
            System.out.println("login required");
        } else{
            userService.userPwUpdate(userEmail, currentPw, newPw);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
