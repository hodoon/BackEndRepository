package com.example.spopiaproj.service;

import com.example.spopiaproj.model.UserDto;
import com.example.spopiaproj.utils.DBConn;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    public boolean isUserAdmin(String userEmail) {
        boolean isAdmin = false;
        try (Connection conn = DBConn.getDBConn();
             PreparedStatement pstmt = conn.prepareStatement("SELECT isAdmin FROM users WHERE userEmail = ?")
        ) {
            pstmt.setString(1, userEmail);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    isAdmin = rs.getBoolean("isAdmin");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdmin;
    }

    public String getUserPwByEmail(String userEmail) {
        try (Connection conn = DBConn.getDBConn()) {
            String query = "Select userPw from users where userEmail = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, userEmail);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String userPw = rs.getString("userPw");
                        rs.close();
                        return userPw;
                    } else {
                        rs.close();
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 사용자 전체 정보 추출 로직
    public UserDto getUserInfoByEmail(String userEmail) {
        UserDto userDto = new UserDto();
        try (Connection conn = DBConn.getDBConn()) {
            String query = "SELECT * FROM users WHERE userEmail = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, userEmail);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    userDto.setUserNo(rs.getString("userNo"));
                    userDto.setUserEmail(rs.getString("userEmail"));
                    userDto.setUserPw(rs.getString("userPw"));
                    userDto.setUserNick(rs.getString("userNick"));
                    userDto.setUserName(rs.getString("userName"));
                    userDto.setUserGender(rs.getString("userGender"));
                    userDto.setUserDob(rs.getString("userDob"));
                    userDto.setUserContact(rs.getString("userContact"));
                    userDto.setUserRole(rs.getString("userRole"));
                    userDto.setUserPhone(rs.getString("userPhone"));
                    userDto.setAdmin(rs.getBoolean("isAdmin"));
                    userDto.setUserState(rs.getString("userState"));
                    userDto.setSignupDate(rs.getString("signupDate"));
                    userDto.setLeaveDate(rs.getString("leaveDate"));
                    rs.close();
                    return userDto;
                } else {
                    rs.close();
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUserActive(String userEmail) {
        try (Connection conn = DBConn.getDBConn()) {
            String query = "SELECT userState FROM users WHERE userEmail = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, userEmail);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    boolean userStatus = rs.getString("userState").equals("active");
                    rs.close();
                    return userStatus;
                } else {
                    rs.close();
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 그 외 오류 발생시 false 반환
        }
    }

    public void signUpUser(UserDto userDto) {
        try (Connection conn = DBConn.getDBConn()) {
            if (!isUserDuplicate(conn, userDto.getUserEmail())) {
                if(userDto.getUserPw() != null){
                    userDto.setUserPw(BCrypt.hashpw(userDto.getUserPw(), BCrypt.gensalt()));
                }
                String query = "INSERT INTO users(userEmail, userPw, userNick, userName, userGender, userDob, userContact) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, userDto.getUserEmail());
                    pstmt.setString(2, userDto.getUserPw());
                    pstmt.setString(3, userDto.getUserNick());
                    pstmt.setString(4, userDto.getUserName());
                    pstmt.setString(5, userDto.getUserGender());
                    pstmt.setString(6, userDto.getUserDob());
                    pstmt.setString(7, userDto.getUserContact());
                    pstmt.executeUpdate();

                    System.out.println("Completed user registration: " + userDto.getUserEmail());
                }
            } else System.out.println("User already exists: " + userDto.getUserEmail());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to Connect DB", e);
        }
    }

    public boolean isUserDuplicate(Connection conn, String userEmail) {
        String query = "SELECT * FROM users WHERE userEmail = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userEmail);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 그 외 오류 발생시 false 반환
        }
    }
    public void userPwUpdate(String userEmail, String currentPw, String newPw){
        if(BCrypt.checkpw(currentPw, getUserPwByEmail(userEmail))){
            try(Connection conn = DBConn.getDBConn()) {
                newPw = BCrypt.hashpw(newPw, BCrypt.gensalt());
                String query = "UPDATE users SET userPw = ? WHERE userEmail = ?";
                try(PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setString(1, newPw);
                    pstmt.setString(2, userEmail);
                    pstmt.executeUpdate();

                    System.out.println("Complete Password updated: " + userEmail);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Fail to Connect DB", e);
            }
        }else {
            System.out.println("Password update failed: " + userEmail);
            throw new RuntimeException("Fail to Connect DB");
        }
    }
    public void userLeave(String userEmail){
        try(Connection conn = DBConn.getDBConn()) {
            String query = "UPDATE users SET leaveDate = CURRENT_TIMESTAMP, " +
                    "userState = 'inactive' WHERE userEmail = ?";
            try(PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setString(1, userEmail);
                pstmt.executeUpdate();

                System.out.println("Complete User leave: " + userEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to Connect DB", e);
        }
    }

    public void updateUser(UserDto userDto) throws SQLException{
        UserDto userInfo = getUserInfoByEmail(userDto.getUserEmail());
        // 입력 된 정보만 정보 수정 되도록 함
        if(userDto.getUserName().isEmpty()) userDto.setUserName(userInfo.getUserName());
        if (userDto.getUserNick().isEmpty()) userDto.setUserNick(userInfo.getUserNick());
        if(userDto.getUserDob().isEmpty()) userDto.setUserDob(userInfo.getUserDob());
        if(userDto.getUserContact().isEmpty()) userDto.setUserContact(userInfo.getUserContact());

        Connection conn = DBConn.getDBConn();
        String query = "UPDATE users SET userName = ?, userNick = ?, userDob = ?, userContact = ? WHERE userEmail = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, userDto.getUserName());
        pstmt.setString(2, userDto.getUserNick());
        pstmt.setString(3, userDto.getUserDob());
        pstmt.setString(4, userDto.getUserContact());
        pstmt.executeUpdate();
        conn.close();
        pstmt.close();
        System.out.println("Complete User information update: " + userDto.getUserEmail());
    }
    public ArrayList<UserDto> getAllUser() throws SQLException{
        ArrayList<UserDto> usersArrayList = new ArrayList<UserDto>();
        Connection conn = DBConn.getDBConn();
        String query = "SELECT userNo, userEmail, userNick, userName, userGender, userDob," +
                "userContact, userRole, signupDate, userState, leaveDate FROM users";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            UserDto userDto = new UserDto();
            userDto.setUserNo(rs.getString("userNo"));
            userDto.setUserEmail(rs.getString("userEmail"));
            userDto.setUserNick(rs.getString("userNick"));
            userDto.setUserName(rs.getString("userName"));
            userDto.setUserGender(rs.getString("userGender"));
            userDto.setUserDob(rs.getString("userDob"));
            userDto.setUserContact(rs.getString("userContact"));
            userDto.setUserRole(rs.getString("userRole"));
            userDto.setSignupDate(rs.getString("signupDate"));
            userDto.setUserState(rs.getString("userState"));
            userDto.setLeaveDate(rs.getString("leaveDate"));
            usersArrayList.add(userDto);
        }
        conn.close();
        pstmt.close();
        rs.close();
        return usersArrayList;
    }
}