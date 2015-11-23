package com.crazydude.sakuraplayer.models;

import java.util.List;

/**
 * Created by kartavtsev.s on 16.06.2015.
 */
public class AlbumModel {

    private ArtistModel artist;
    private String name;
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
