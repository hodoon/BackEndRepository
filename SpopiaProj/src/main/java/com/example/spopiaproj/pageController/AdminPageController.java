package com.example.spopiaproj.pageController;

import com.example.spopiaproj.HelloServlet;
import com.example.spopiaproj.model.UserDto;
import com.example.spopiaproj.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/admin-page")
public class AdminPageController extends HelloServlet {
    UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ArrayList<UserDto> userList;

        try {
            userList = userService.getAllUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("userList", userList);

        String viewPath = "/admin/admin-page.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
    }
}
