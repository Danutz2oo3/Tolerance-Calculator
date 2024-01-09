package com.Danutz.tolerancecalculator.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    public UserEntity getUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    public UserEntity registerUser(String username, String password, String email) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findUserById(id);
    }
}
