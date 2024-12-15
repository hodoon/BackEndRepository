package com.example.spopiaproj.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/checkLogin")
public class CheckLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // 기존 세션 가져오기, 없으면 null
        if (session != null) {
            String userEmail = (String) session.getAttribute("userEmail");
            String userRole = (String) session.getAttribute("userRole");

            if ("ADMIN".equals(userRole)) {
                // Redirect to admin page for admin users
                response.sendRedirect(request.getContextPath() + "/admin-page");
                return;
            }

            if (userEmail != null) {
                // Redirect to user myPage for logged-in users
                response.sendRedirect(request.getContextPath() + "/user/my-page");
                return;
            }
        }

        // Redirect to login page for unauthenticated users
        response.sendRedirect(request.getContextPath() + "/user/login");
    }
}
