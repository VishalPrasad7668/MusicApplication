package com.niit.UserTrackService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Users {
    @Id
    private String userId;
    private String password;
    private String userName;
    private String phoneNo;
    private List<Tracks> tracks;

    public Users() {
    }

    public Users(String userId, String password, String userName, String phoneNo, List<Tracks> tracks) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phoneNo = phoneNo;
        this.tracks = tracks;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<Tracks> getTracks() {
        return tracks;
    }

    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", tracks=" + tracks +
                '}';
    }
}
