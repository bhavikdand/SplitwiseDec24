package org.example.splitwisedec24;

import org.example.splitwisedec24.commands.Command;
import org.example.splitwisedec24.commands.CommandRegistry;
import org.example.splitwisedec24.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseDec24Application implements CommandLineRunner {

//    @Autowired
//    private UserController userController;
    public static void main(String[] args) {
        SpringApplication.run(SplitwiseDec24Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Awaiting input");
            String userCommand = scanner.nextLine();
            try {
                Command command = CommandRegistry.getInstance().get(userCommand);
                command.execute(userCommand);
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        // Implement login command as well
//        String userName = "abc";
//        String password = "password1122";
//        System.out.println(userController.login(userName, password));
    }
}
