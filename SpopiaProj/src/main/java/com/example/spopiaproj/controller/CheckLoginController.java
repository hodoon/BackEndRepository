package com.example.spopiaproj.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/checkLogin")
public class CheckLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // 기존 세션 가져오기, 없으면 null
        String userName = (String) session.getAttribute("userName");
        String userRole = (String) session.getAttribute("userRole");

        if (userName != null) {
            // 세션에 로그인 정보가 있는 경우 마이페이지로 이동
            response.sendRedirect(request.getContextPath() + "/user/myPage.jsp");
        } else {
            // 세션에 로그인 정보가 없는 경우 로그인 페이지로 이동
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
        }
    }
}
