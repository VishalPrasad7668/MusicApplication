package com.niit.UserTrackService.domain;

import org.springframework.data.annotation.Id;

public class Tracks {
    @Id
    private int trackId;
    private String trackName;
    private Artist artist;

    public Tracks() {
    }

    public Tracks(int trackId, String trackName, Artist artist) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.artist = artist;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Tracks{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", artist=" + artist +
                '}';
    }
}
