package com.example.spopiaproj.controller;

import com.example.spopiaproj.service.UserLoginService;
import com.example.spopiaproj.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/user/loginProc")
public class LoginController extends HttpServlet {
    UserService userService = new UserService();
    UserLoginService userLoginService = new UserLoginService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String userEmail = req.getParameter("userEmail");
        String userPw = req.getParameter("userPw");;
        // 로그인 로직 수행
        // 로그인 성공시 메인 페이지, 실패시 다시 로그인 페이지
        if(userLoginService.loginUser(userEmail, userPw)) {
            session.setAttribute("isLogin", "true");
            session.setAttribute("userEmail", userEmail);
            session.setAttribute("userNick", userService.getUserNick(userEmail));
            session.setAttribute("userRole", userService.getUserRole(userEmail));
            if(userService.isUserAdmin(userEmail)){
                session.setAttribute("isAdmin", "true");
            }
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/");
        } else {
            session.setAttribute("message", "비밀번호가 틀립니다.");
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/user/login");
        }
    }
}
