package com.example.spopiaproj.controller;



import com.example.spopiaproj.model.UserDto;
import com.example.spopiaproj.service.AdminUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static com.mysql.cj.conf.PropertyKey.logger;

@WebServlet(name = "AdminUserController", urlPatterns = {"/admin/users", "/admin/addUser", "/admin/deleteUser"})
public class AdminUserController extends HttpServlet {
    private final AdminUserService adminUserService;
    private static final Logger logger = Logger.getLogger(AdminUserController.class.getName());

    public AdminUserController() {
        this.adminUserService = new AdminUserService(); // Initialize the service here
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getServletPath();

            switch (action) {
                case "/admin/users":
                    handleUserList(request, response);
                    break;
                case "/admin/deleteUser":
                    handleDeleteUser(request, response);
                    break;
                case "/admin/editUser":
                    handleEditUser(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/admin/addUser".equals(action)) {
            handleAddUser(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private UserDto extractUserFromRequest(HttpServletRequest request) {
        UserDto user = new UserDto();
        user.setEmail(request.getParameter("userEmail"));
        user.setPassword(request.getParameter("userPassword"));
        user.setName(request.getParameter("userName"));
        user.setNickname(request.getParameter("userNickname"));
        user.setSex(request.getParameter("userSex").charAt(0));
        user.setBirthDate(java.sql.Date.valueOf(request.getParameter("userBirth")));
        user.setPhone(request.getParameter("userTel"));
        return user;
    }

    // 사용자 목록 조회
    private void handleUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> userList = adminUserService.getAllUsers();
        logger.info("User List: " + userList); // Use a logger instead of System.out.println
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/views/admin-page.jsp").forward(request, response);
    }

    // 사용자 추가
    private void handleAddUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDto user = new UserDto();
        user.setEmail(request.getParameter("userEmail"));
        user.setPassword(request.getParameter("userPassword"));
        user.setName(request.getParameter("userName"));
        user.setNickname(request.getParameter("userNickname"));
        user.setSex(request.getParameter("userSex").charAt(0));
        user.setBirthDate(java.sql.Date.valueOf(request.getParameter("userBirth")));
        user.setPhone(request.getParameter("userTel"));

        boolean isAdded = adminUserService.addUser(user);

        if (isAdded) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "사용자 추가 중 문제가 발생했습니다.");
        }
    }

    // 사용자 삭제
    private void handleDeleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userEmail = request.getParameter("userEmail");
        boolean isDeleted = adminUserService.deleteUser(userEmail);

        if (isDeleted) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "사용자 삭제 중 문제가 발생했습니다.");
        }
    }

    // 사용자 수정
    private void handleEditUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDto user = new UserDto();
        user.setEmail(request.getParameter("userEmail"));
        user.setPassword(request.getParameter("userPassword"));
        user.setName(request.getParameter("userName"));
        user.setNickname(request.getParameter("userNickname"));
        user.setSex(request.getParameter("userSex").charAt(0));
        user.setBirthDate(java.sql.Date.valueOf(request.getParameter("userBirth")));
        user.setPhone(request.getParameter("userTel"));

        boolean isUpdated = adminUserService.updateUser(user);

        if (isUpdated) {
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "사용자 수정 중 문제가 발생했습니다.");
        }
    }
}
