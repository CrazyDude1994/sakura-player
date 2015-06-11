package com.crazydude.sakuraplayer.models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Crazy on 26.04.2015.
 */
@Table(name = "Artist")
public class ArtistModel {

    @Column(name = "Name")
    private String mArtistName;

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String mArtistName) {
        this.mArtistName = mArtistName;
    }
}
