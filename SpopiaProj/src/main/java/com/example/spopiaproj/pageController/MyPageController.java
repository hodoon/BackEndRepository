package com.example.spopiaproj.pageController;

import com.example.spopiaproj.HelloServlet;
import com.example.spopiaproj.model.UserDto;
import com.example.spopiaproj.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/my-page")
public class MyPageController extends HelloServlet {
    UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        UserDto userDto;
        try {
            userDto = userService.getUserInfoByEmail(session.getAttribute("userEmail").toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("userDto", userDto);

        String viewPath = "/user/myPage.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);

    }
}
