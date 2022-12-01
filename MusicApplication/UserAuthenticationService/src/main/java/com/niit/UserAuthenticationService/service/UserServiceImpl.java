package com.niit.UserAuthenticationService.service;

import com.niit.UserAuthenticationService.domain.User;
import com.niit.UserAuthenticationService.exception.UserAlreadyExistException;
import com.niit.UserAuthenticationService.exception.UserNotFoundException;
import com.niit.UserAuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistException {
        return userRepository.save(user);
    }

    @Override
    public User findUserByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
        User user = userRepository.findUserByUserIdAndPassword(userId,password);
        if (user==null){
            throw new UserNotFoundException();
        }
        return user;
    }
}
