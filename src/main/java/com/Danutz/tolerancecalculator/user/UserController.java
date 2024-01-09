package com.Danutz.tolerancecalculator.user;

import com.Danutz.tolerancecalculator.errorResponse.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = {"api/v1/users"})
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = {"login-by-username"})
    public ResponseEntity<UserEntity> getUserByUsernameAndPassword(
            @RequestParam String username,
            @RequestParam String password) {
        UserEntity user = userService.getUserByUsernameAndPassword(username, password);
        if(user == null){
            throw new CustomException(400, "user not found");
        }
        else if(user.getUsername() == null || user.getPassword() == null){
            throw new CustomException(400, "user not found");
        }
        else if(user.getUsername().equals(username) && user.getPassword().equals(password)){
            return ResponseEntity.ok(user);
        }
        else{
            throw new CustomException(400, "user not found");
        }
    }

    @GetMapping(path = {"login-by-email"})
    public ResponseEntity<UserEntity> getUserByEmailAndPassword(
            @RequestParam String email,
            @RequestParam String password) {
        UserEntity user = userService.getUserByEmailAndPassword(email, password);
        return ResponseEntity.ok(user);
    }

    @PostMapping(path = {"register"})
    public ResponseEntity<UserEntity> registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) {
        if(username == null || password == null || email == null){
            throw new CustomException(400, "invalid credentials");
        }
        else if(userService.getUserByEmail(email) != null && userService.getUserByUsername(username) != null){
            throw new CustomException(400, "user already exists");
        }
        else if(userService.getUserByUsername(username) != null){
            throw new CustomException(400, "invalid username");
        }
        else if(userService.getUserByEmail(email) != null){
            throw new CustomException(400, "invalid email");
        }
        else {
            UserEntity user = userService.registerUser(username, password, email);
            return ResponseEntity.ok(user);
        }
    }
}
