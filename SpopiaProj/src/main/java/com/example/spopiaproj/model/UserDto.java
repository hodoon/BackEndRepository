package com.example.spopiaproj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDto {
    private String userNo;
    private String userEmail;
    private String userPw;
    private String userNick;
    private String userName;
    private String userGender;
    private String userDob;
    private String userContact;
    private String userRole;
    private boolean isAdmin;
    private String userState;
    private String signupDate;
    private String leaveDate;
}
