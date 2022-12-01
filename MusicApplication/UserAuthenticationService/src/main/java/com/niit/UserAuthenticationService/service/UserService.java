package com.niit.UserAuthenticationService.service;

import com.niit.UserAuthenticationService.domain.User;
import com.niit.UserAuthenticationService.exception.UserAlreadyExistException;
import com.niit.UserAuthenticationService.exception.UserNotFoundException;

public interface UserService {
    public User addUser(User user) throws UserAlreadyExistException;
    public User findUserByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
