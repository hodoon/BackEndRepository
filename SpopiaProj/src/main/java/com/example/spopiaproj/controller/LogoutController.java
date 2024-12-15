package com.example.spopiaproj.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/logout")
public class LogoutController extends HttpServlet {
    // 로그아웃 기능 구현
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        if (session.getAttribute("isLogin").equals("true")) {
            session.removeAttribute("isLogin");
            session.removeAttribute("userEmail");
            if (session.getAttribute("isAdmin") != null) {
                session.removeAttribute("isAdmin");
            }
            System.out.println("Successfully logged out.");
        } else {
            System.out.println("You are not logged in.");
        }
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // POST 요청도 GET 요청으로 위임
        doGet(req, resp);
    }
}
