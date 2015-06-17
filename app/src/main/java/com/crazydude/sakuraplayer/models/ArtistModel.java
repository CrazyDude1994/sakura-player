package com.crazydude.sakuraplayer.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Crazy on 26.04.2015.
 */
@Table(name = "Artist")
public class ArtistModel extends Model {

    @Column(name = "Name")
    private String artistName;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String mArtistName) {
        this.artistName = mArtistName;
    }
}
