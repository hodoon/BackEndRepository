package com.example.spopiaproj.service;

import com.example.spopiaproj.model.UserDto;
import org.mindrot.jbcrypt.BCrypt;

public class UserLoginService {
    UserService userService = new UserService();

    // 사용자 로그인 처리
    public boolean loginUser(String userEmail, String userPw){
        UserDto userInfo = userService.getUserInfoByEmail(userEmail);
        try {
            if(!userInfo.getUserState().equals("active")){
                System.out.println("!!! User is not active !!!");
                return false;
            } else {
                //DB에서 userEmail로 사용자를 검색하여 비밀번호를 가져옴
                String hashedPw = userInfo.getUserPw();
                if (BCrypt.checkpw(userPw, hashedPw)){
                    System.out.println("!!! User login success !!!");
                    return true;
                } else {
                    System.out.println("!!! User Password Wrong !!!");
                    return false;
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("!!! User not found !!!");
            return false;
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            System.out.println("!! BCrypt password check error !!");
            return false;
        }
    }
}
