package com.example.spopiaproj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/spopiadb";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 클래스 로드
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC 드라이버를 로드하지 못했습니다.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
