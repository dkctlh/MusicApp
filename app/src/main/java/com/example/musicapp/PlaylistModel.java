package com.example.musicapp;

public class PlaylistModel {


    private String playlistName;
    private String musicPath;


    // creating getter and setter methods
    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }



    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }



    // constructor
    public PlaylistModel(String playlistName,  String musicPath) {
        this.playlistName = playlistName;
        this.musicPath = musicPath;
    }
}

