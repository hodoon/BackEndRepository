package com.example.spopiaproj.controller;

import com.example.spopiaproj.model.UserDto;
import com.example.spopiaproj.service.UserService;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/auth/register")
public class UserController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[DEBUG] Received registration request.");

        // 사용자 입력값 추출
        String email = request.getParameter("username");
        String password = request.getParameter("password1");
        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
        String sexParam = request.getParameter("sex");
        String birthYear = request.getParameter("birthday_year");
        String birthMonth = request.getParameter("birthday_month");
        String birthDay = request.getParameter("birthday_day");
        String phone = request.getParameter("phone");

        // 유효성 검증
        if (email == null || password == null || name == null || nickname == null ||
                sexParam == null || birthYear == null || birthMonth == null || birthDay == null || phone == null) {
            System.err.println("[ERROR] Missing required fields.");
            response.sendRedirect("/error.jsp");
            return;
        }

        String sex;
        try {
            sex = sexParam;
        } catch (Exception e) {
            System.err.println("[ERROR] Invalid gender parameter.");
            response.sendRedirect("/error.jsp");
            return;
        }

        try {
            // 사용자 DTO 생성
            UserDto user = new UserDto();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setNickname(nickname);
            user.setSex(sex);

            // 생년월일 병합 및 파싱
            String birthDateStr = birthYear + "-" + birthMonth + "-" + birthDay;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthDate(dateFormat.parse(birthDateStr));
            user.setPhone(phone);

            System.out.println("[DEBUG] User object created: " + user);

            // 사용자 등록 서비스 호출
            boolean isRegistered = userService.registerUser(user);
            if (isRegistered) {
                System.out.println("[DEBUG] User registered successfully: " + user.getEmail());
                response.sendRedirect(request.getContextPath() + "/views/loginPage.jsp");
            } else {
                System.err.println("[ERROR] User registration failed.");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } catch (ParseException e) {
            System.err.println("[ERROR] Date parsing failed: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        } catch (Exception e) {
            System.err.println("[ERROR] Unexpected error: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
