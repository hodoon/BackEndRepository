package com.example.spopiaproj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConn {
        public static Connection conn;

        public static Connection getDBConn() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String dbUrl = "jdbc:mysql://localhost:3306/spopiadb?useUnicode=true&characterEncoding=UTF-8";
                String dbUser = "root";
                String dbPw = "admin";
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
                return conn;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("MySQL JDBC Driver not found");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to connect to DB", e);
            }
        }

        public static void dbClose(PreparedStatement pstmt, Connection conn) throws SQLException {
            pstmt.close();
            conn.close();
        }
}
