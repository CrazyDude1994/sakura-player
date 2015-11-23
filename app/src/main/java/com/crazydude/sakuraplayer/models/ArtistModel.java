package com.crazydude.sakuraplayer.models;

import java.util.List;

/**
 * Created by Crazy on 26.04.2015.
 */
public class ArtistModel {

    private String artistName;

    private List<AlbumModel> cachedAlbums;

    public List<AlbumModel> getAlbums() {
        if (cachedAlbums == null) {

        }
        return cachedAlbums;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String mArtistName) {
        this.artistName = mArtistName;
    }
}
