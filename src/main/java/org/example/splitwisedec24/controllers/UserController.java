package org.example.splitwisedec24.controllers;


import org.example.splitwisedec24.dtos.ResponseStatus;
import org.example.splitwisedec24.dtos.SignupRequestDto;
import org.example.splitwisedec24.dtos.SignupResponseDto;
import org.example.splitwisedec24.models.User;
import org.example.splitwisedec24.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignupResponseDto signUp(SignupRequestDto requestDto){
        SignupResponseDto responseDto = new SignupResponseDto();
        try {
            User user = this.userService.signupUser(requestDto.getName(), requestDto.getPhoneNumber(), requestDto.getPassword());
            responseDto.setUserId(user.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }

    public boolean login(String userName, String password){
        try {
            return this.userService.login(userName, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
