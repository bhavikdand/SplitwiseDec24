package org.example.splitwisedec24.services;


import org.example.splitwisedec24.exceptions.UserAlreadyPresentException;
import org.example.splitwisedec24.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    public User signupUser(String name, String phoneNumber, String password) throws UserAlreadyPresentException;

    public boolean login(String name, String password) throws UsernameNotFoundException;
}
