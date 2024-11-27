package com.example.spopiaproj.dao;

import com.example.spopiaproj.model.UserDto;
import com.example.spopiaproj.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    public boolean saveUser(UserDto user) {
        String userInsertQuery = "INSERT INTO usertbl (userEmail, userPassword, userName, userNickname, userSex, userBirth, userTel) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String roleMappingQuery = "INSERT INTO user_role (userEmail, roleId) VALUES (?, 2)";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // 트랜잭션 시작

            try (PreparedStatement userStmt = conn.prepareStatement(userInsertQuery);
                 PreparedStatement roleStmt = conn.prepareStatement(roleMappingQuery)) {

                // usertbl에 사용자 삽입
                System.out.println("[DEBUG] Preparing to save user: " + user.getEmail());
                userStmt.setString(1, user.getEmail());
                userStmt.setString(2, user.getPassword());
                userStmt.setString(3, user.getName());
                userStmt.setString(4, user.getNickname());
                userStmt.setString(5, String.valueOf(user.getSex()));
                userStmt.setDate(6, new java.sql.Date(user.getBirthDate().getTime()));
                userStmt.setString(7, user.getPhone());

                System.out.println("[DEBUG] Executing user insert query: " + userInsertQuery);
                userStmt.executeUpdate();

                // user_role에 권한 매핑
                roleStmt.setString(1, user.getEmail());
                System.out.println("[DEBUG] Executing role mapping query: " + roleMappingQuery);
                roleStmt.executeUpdate();

                conn.commit(); // 트랜잭션 커밋
                System.out.println("[DEBUG] User saved successfully: " + user.getEmail());
                return true;
            } catch (SQLException e) {
                if (conn != null) {
                    conn.rollback(); // 오류 발생 시 롤백
                    System.err.println("[ERROR] Transaction rolled back due to error: " + e.getMessage());
                }
                throw e; // 예외 재발생
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Failed to save user: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // 자동 커밋 모드 복원
                    conn.close(); // 연결 닫기
                } catch (SQLException e) {
                    System.err.println("[ERROR] Failed to close connection: " + e.getMessage());
                }
            }
        }
    }
}