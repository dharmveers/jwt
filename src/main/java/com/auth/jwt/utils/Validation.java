package com.auth.jwt.utils;

import com.auth.jwt.dtos.LoginUsrDto;
import com.auth.jwt.dtos.RegisterUsrDto;

public class Validation {
    public static String signupUserCheck(RegisterUsrDto usrDto){
        if(usrDto==null){
            return "User must not be null";
        } else if (usrDto.getFullName()==null || usrDto.getFullName().isEmpty()) {
            return "User name must not be null";
        } else if (usrDto.getEmail()==null || usrDto.getEmail().trim().isEmpty()) {
            return "User email must not be null";
        } else if (usrDto.getPassword()==null || usrDto.getPassword().trim().isEmpty()) {
            return "User password must not be null";
        }
        return "success";
    }
    public static String loginCheck(LoginUsrDto dto){
        if(dto.getEmail()==null || dto.getEmail().isEmpty()){
            return "User email is required";
        } else if (dto.getPassword()==null || dto.getPassword().isEmpty()) {
            return "User password is required";
        }
        return "success";
    }
}
