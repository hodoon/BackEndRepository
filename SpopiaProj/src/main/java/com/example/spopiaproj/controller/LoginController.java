package com.example.spopiaproj.controller;

import com.example.spopiaproj.utils.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");

        // 입력 값 검증
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            response.sendRedirect("/login.jsp?error=empty_fields");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT u.userEmail, u.userName, r.roleName " +
                    "FROM usertbl u " +
                    "JOIN user_role ur ON u.userEmail = ur.userEmail " +
                    "JOIN roletbl r ON ur.roleId = r.roleId " +
                    "WHERE u.userEmail = ? AND u.userPassword = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String userName = rs.getString("userName");
                String roleName = rs.getString("roleName");

                // 세션 설정
                HttpSession session = request.getSession();
                session.setAttribute("userEmail", email);
                session.setAttribute("userName", userName);
                session.setAttribute("roleName", roleName);

                System.out.println("[DEBUG] User logged in: " + email + ", Role: " + roleName);

                // 역할에 따라 페이지 리다이렉션
                if ("ADMIN".equalsIgnoreCase(roleName)) {
                    response.sendRedirect(request.getContextPath() + "/admin/users");
                } else {
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                }
            } else {
                System.err.println("[ERROR] Invalid login attempt: " + email);
                response.sendRedirect(request.getContextPath() + "/login.jsp?error=invalid_credentials");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
