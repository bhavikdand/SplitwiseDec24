package org.example.splitwisedec24.services;


import org.example.splitwisedec24.exceptions.UserAlreadyPresentException;
import org.example.splitwisedec24.models.User;
import org.example.splitwisedec24.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signupUser(String name, String phoneNumber, String password) throws UserAlreadyPresentException {
        if(userRepository.findByName(name).isPresent()){
            throw new UserAlreadyPresentException("User is already present in DB");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User();
        user.setPassword(encodedPassword);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);

        return userRepository.save(user);
    }

    @Override
    public boolean login(String name, String password) throws UsernameNotFoundException {
        User user = this.userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("User name is not present in DB"));

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }
}
