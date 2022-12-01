package com.niit.UserAuthenticationService.controller;

import com.niit.UserAuthenticationService.domain.User;
import com.niit.UserAuthenticationService.exception.UserAlreadyExistException;
import com.niit.UserAuthenticationService.exception.UserNotFoundException;
import com.niit.UserAuthenticationService.service.SecurityTokenGenerator;
import com.niit.UserAuthenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/userservice/")
public class UserController {
    private SecurityTokenGenerator securityTokenGenerator;
    private UserService userService;
    @Autowired
    public UserController(SecurityTokenGenerator securityTokenGenerator, UserService userService) {
        this.securityTokenGenerator = securityTokenGenerator;
        this.userService = userService;
    }

    @RequestMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistException {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }
    @RequestMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException{
        Map<String,String> map = null;
        try {
            User user1 = userService.findUserByUserIdAndPassword(user.getUserId(),user.getPassword());
            if(user1.getUserId().equals(user.getUserId()) && user1.getPassword().equals(user.getPassword())){
                map = securityTokenGenerator.generateTokenForUser(user);
            }
            return new ResponseEntity<>(map,HttpStatus.FOUND);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception e){
            return new ResponseEntity<>("WRONG INPUT",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
