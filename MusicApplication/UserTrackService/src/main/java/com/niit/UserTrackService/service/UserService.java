package com.niit.UserTrackService.service;

import com.niit.UserTrackService.domain.Tracks;
import com.niit.UserTrackService.domain.Users;
import com.niit.UserTrackService.exception.TrackAlreadyExistException;
import com.niit.UserTrackService.exception.TrackNotFoundException;
import com.niit.UserTrackService.exception.UserAlreadyExistException;
import com.niit.UserTrackService.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    public Users addUser(Users users) throws UserAlreadyExistException;
    public Users addTrackForUser(String userId, Tracks tracks) throws UserNotFoundException, TrackAlreadyExistException;
    public List<Tracks> getAllTracksOfUser(String userId) throws UserNotFoundException;
    public String deleteTrackOfUser(String userId, int trackId) throws TrackNotFoundException,UserNotFoundException;
    public Users updateTrackOfUser(String userId,Tracks tracks) throws UserNotFoundException;
}
