package com.example.spopiaproj.dao;

import com.example.spopiaproj.model.UserDto;
import com.example.spopiaproj.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminUserDao {
    // 사용자 목록 조회
    public List<UserDto> getAllUsers() {
        String query = "SELECT userEmail, userName, userNickname, userSex, userBirth, userTel FROM usertbl";
        List<UserDto> userList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserDto user = new UserDto();
                user.setEmail(rs.getString("userEmail"));
                user.setName(rs.getString("userName"));
                user.setNickname(rs.getString("userNickname"));
                user.setSex(rs.getString("userSex"));
                user.setBirthDate(rs.getDate("userBirth"));
                user.setPhone(rs.getString("userTel"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Fetched Users: " + userList); // Debugging statement
        return userList;
    }

    // 사용자 삭제
    public boolean deleteUser(String email) {
        String query = "DELETE FROM usertbl WHERE userEmail = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 사용자 추가 (중복된 로직이 있을 경우 UserDAO와 공용 메서드로 전환 가능)
    public boolean addUser(UserDto user) {
        String userInsertQuery = "INSERT INTO usertbl (userEmail, userPassword, userName, userNickname, userSex, userBirth, userTel) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String roleMappingQuery = "INSERT INTO user_role (userEmail, roleId) VALUES (?, 2)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement userStmt = conn.prepareStatement(userInsertQuery);
                 PreparedStatement roleStmt = conn.prepareStatement(roleMappingQuery)) {

                userStmt.setString(1, user.getEmail());
                userStmt.setString(2, user.getPassword());
                userStmt.setString(3, user.getName());
                userStmt.setString(4, user.getNickname());
                userStmt.setString(5, String.valueOf(user.getSex()));
                userStmt.setDate(6, new java.sql.Date(user.getBirthDate().getTime()));
                userStmt.setString(7, user.getPhone());
                userStmt.executeUpdate();

                roleStmt.setString(1, user.getEmail());
                roleStmt.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 사용자 수정
    public boolean editUser(UserDto user) {
        String query = "UPDATE usertbl SET userPassword = ?, userName = ?, userNickname = ?, userSex = ?, userBirth = ?, userTel = ? WHERE userEmail = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getNickname());
            stmt.setString(4, String.valueOf(user.getSex()));
            stmt.setDate(5, new java.sql.Date(user.getBirthDate().getTime()));
            stmt.setString(6, user.getPhone());
            stmt.setString(7, user.getEmail());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public UserDto getUserByEmail(String userEmail) {
        String query = "SELECT userEmail, userPassword, userName, userNickname, userSex, userBirth, userTel FROM usertbl WHERE userEmail = ?";
        UserDto user = null; // null로 초기화하여 사용자가 없을 경우 명확히 반환

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userEmail); // 첫 번째 파라미터 설정
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // 사용자가 존재할 경우 객체 생성
                    user = new UserDto();
                    user.setEmail(rs.getString("userEmail"));
                    user.setPassword(rs.getString("userPassword"));
                    user.setName(rs.getString("userName"));
                    user.setNickname(rs.getString("userNickname"));
                    user.setSex(rs.getString("userSex"));
                    user.setBirthDate(rs.getDate("userBirth"));
                    user.setPhone(rs.getString("userTel"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user with email: " + userEmail);
            e.printStackTrace();
        }
        System.out.println("Fetched User: " + user); // Debugging statement
        return user;
    }
}

