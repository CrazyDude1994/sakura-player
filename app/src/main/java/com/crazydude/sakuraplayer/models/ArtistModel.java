package com.crazydude.sakuraplayer.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crazy on 26.04.2015.
 */
@Table(name = "Artist")
public class ArtistModel extends Model {

    @Column(name = "Name")
    private String artistName;

    public List<AlbumModel> getAlbums() {
        return getMany(AlbumModel.class, "Artist");
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String mArtistName) {
        this.artistName = mArtistName;
    }
}
