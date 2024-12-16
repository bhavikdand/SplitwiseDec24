package org.example.splitwisedec24.commands;

import org.example.splitwisedec24.controllers.UserController;
import org.example.splitwisedec24.dtos.SignupRequestDto;
import org.example.splitwisedec24.dtos.SignupResponseDto;
import org.example.splitwisedec24.exceptions.InvalidCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCommand implements Command{

    private UserController userController;

    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
        CommandRegistry.getInstance().register("Register", this);
    }

    @Override
    public void execute(String command) throws Exception {
        //Validate the command
        String[] commandArr = command.split(" ");
        if(commandArr.length != 4){
            throw new InvalidCommandException("Register user command needs 4 paramaters");
        }
        if(!commandArr[0].equals("Register")){
            throw new InvalidCommandException("Register keyword is missing in the command");
        }
        //Add more validations, for email, phone and password
        // If the command is valid, then we can go for execution
        SignupRequestDto requestDto = new SignupRequestDto();
        requestDto.setName(commandArr[1]);
        requestDto.setPhoneNumber(commandArr[2]);
        requestDto.setPassword(commandArr[3]);

        SignupResponseDto signupResponseDto = this.userController.signUp(requestDto);

        System.out.println("User register status: " + signupResponseDto.getResponseStatus());
        System.out.println("UserId: " + signupResponseDto.getUserId());

    }
}
