package com.crazydude.sakuraplayer.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by kartavtsev.s on 16.06.2015.
 */
@Table(name = "Albums")
public class AlbumModel extends Model {

    @Column(name = "Artist")
    private ArtistModel artist;
    @Column(name = "Name")
    private String name;
    @Column(name = "AlbumArt")
    private String albumArt;

    private ArtistModel cachedArtist;
    private List<TrackModel> cachedTracks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumArtPath() {
        return albumArt;
    }

    public void setAlbumArtPath(String path) {
        this.albumArt = path;
    }

    public List<TrackModel> tracks() {
        if (cachedTracks == null) {
            cachedTracks = getMany(TrackModel.class, "Album");
        }
        return cachedTracks;
    }

    public ArtistModel getArtist() {
        if (cachedArtist == null) {
            cachedArtist = artist;
        }
        return cachedArtist;
    }

    public void setArtist(ArtistModel mArtist) {
        this.artist = mArtist;
    }
}
