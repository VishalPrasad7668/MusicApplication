package com.niit.UserTrackService.controller;

import com.niit.UserTrackService.domain.Tracks;
import com.niit.UserTrackService.domain.Users;
import com.niit.UserTrackService.exception.TrackAlreadyExistException;
import com.niit.UserTrackService.exception.TrackNotFoundException;
import com.niit.UserTrackService.exception.UserAlreadyExistException;
import com.niit.UserTrackService.exception.UserNotFoundException;
import com.niit.UserTrackService.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/trackapp/v1/")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody Users user) throws UserAlreadyExistException {
        try{
            user.setTracks(new ArrayList<>());
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        }catch (Exception e){
            throw new UserAlreadyExistException();
        }
    }

    @PutMapping("/track/addTrack")
    public ResponseEntity<?> addTrackToTheUser(HttpServletRequest request, @RequestBody Tracks tracks) throws UserNotFoundException, TrackAlreadyExistException {
        String userId = null;
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            userId = claims.getSubject();
            System.out.println("UserId from Claims "+userId);
            return new ResponseEntity<>(userService.addTrackForUser(userId,tracks),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (TrackAlreadyExistException e){
            throw new TrackAlreadyExistException();
        }
    }

    @DeleteMapping("/track/deleteTrack/{userId}/{trackId}")
    public ResponseEntity<?> deleteTrackOfUser(@PathVariable String userId,@PathVariable int trackId) throws UserNotFoundException, TrackNotFoundException {
        try {
            return new ResponseEntity<>(userService.deleteTrackOfUser(userId,trackId),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (TrackNotFoundException p){
            throw new TrackNotFoundException();
        }
    }

    @PutMapping("/track/updateTrack/{userId}")
    public ResponseEntity<?> updateTrackOfUser(@PathVariable String userId,@RequestBody Tracks tracks) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userService.updateTrackOfUser(userId,tracks),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/track/userTracks/{userId}")
    public ResponseEntity<?> getTracksFromUser(@PathVariable String userId) throws UserNotFoundException {
        try{
            return new ResponseEntity<>(userService.getAllTracksOfUser(userId),HttpStatus.FOUND);
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }
}
