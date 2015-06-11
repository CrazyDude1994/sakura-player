package com.crazydude.sakuraplayer.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.ArrayList;

/**
 * Created by kartavtsev.s on 11.06.2015.
 */
@Table(name = "Playlist")
public class PlaylistModel extends Model {

    @Column(name = "Tracks")
    private ArrayList<TrackModel> tracks;

    @Column(name = "Name", unique = true)
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
