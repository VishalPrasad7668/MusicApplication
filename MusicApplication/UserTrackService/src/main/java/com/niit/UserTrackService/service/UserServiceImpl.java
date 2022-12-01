package com.niit.UserTrackService.service;

import com.niit.UserTrackService.domain.Tracks;
import com.niit.UserTrackService.domain.Users;
import com.niit.UserTrackService.exception.TrackAlreadyExistException;
import com.niit.UserTrackService.exception.TrackNotFoundException;
import com.niit.UserTrackService.exception.UserAlreadyExistException;
import com.niit.UserTrackService.exception.UserNotFoundException;
import com.niit.UserTrackService.proxy.UserProxy;
import com.niit.UserTrackService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private UserProxy userProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public Users addUser(Users users) throws UserAlreadyExistException {
        if (userRepository.findById(users.getUserId()).isPresent()){
            throw new UserAlreadyExistException();
        }
        Users user1 = userRepository.save(users);
        if (!(user1.getUserId().isEmpty())){
            ResponseEntity responseEntity = userProxy.saveUser(users);
        }
        return user1;
    }

    @Override
    public Users addTrackForUser(String userId, Tracks tracks) throws UserNotFoundException, TrackAlreadyExistException {
        if (userRepository.findById(userId).isEmpty()){
            throw new  UserNotFoundException();
        }
        Users user = userRepository.findByUserId(userId);
        if (user.getTracks()==null){
            user.setTracks(Arrays.asList(tracks));
        }
        else {
            List<Tracks> tracksList = user.getTracks();
            tracksList.add(tracks);
            user.setTracks(tracksList);
        }
        return userRepository.save(user);
    }

    @Override
    public List<Tracks> getAllTracksOfUser(String userId) throws UserNotFoundException {
        if (userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findById(userId).get().getTracks();
    }

    @Override
    public String deleteTrackOfUser(String userId, int trackId) throws TrackNotFoundException, UserNotFoundException {
        boolean result = false;
        if (userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        Users user = userRepository.findById(userId).get();
        List<Tracks> tracksList = user.getTracks();
        result = tracksList.removeIf(obj-> obj.getTrackId()==trackId);

        if(!result){
            throw new TrackNotFoundException();
        }
        user.setTracks(tracksList);
        userRepository.save(user);
        return "Track with track Id = "+trackId+" is deleted";
    }

    @Override
    public Users updateTrackOfUser(String userId, Tracks tracks) throws UserNotFoundException {
        if (userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        Users user = userRepository.findById(userId).get();
        List<Tracks> tracksList = user.getTracks();
        Iterator<Tracks> iterator = tracksList.iterator();
        while (iterator.hasNext()){
            Tracks track1 = iterator.next();
            if(track1.getTrackId()==tracks.getTrackId()){
                track1.setTrackName(tracks.getTrackName());
                track1.setArtist(tracks.getArtist());
            }
        }

        user.setTracks(tracksList);
        return userRepository.save(user);
    }
}
