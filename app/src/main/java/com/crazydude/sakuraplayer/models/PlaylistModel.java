package com.crazydude.sakuraplayer.models;

import java.util.ArrayList;

/**
 * Created by kartavtsev.s on 11.06.2015.
 */
public class PlaylistModel {

    private ArrayList<TrackModel> tracks;
    private String name;

    public PlaylistModel(ArrayList<TrackModel> tracks, String name) {
        this.tracks = tracks;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TrackModel> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<TrackModel> tracks) {
        this.tracks = tracks;
    }
}
