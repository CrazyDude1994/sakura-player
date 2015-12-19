package com.crazydude.sakuraplayer.models;

import android.os.Parcelable;

import lombok.Data;

/**
 * Created by Crazy on 26.04.2015.
 */
@Data
public class TrackModel {

    private ArtistModel artist;
    private String trackName;
    private String trackPath;
    private AlbumModel album;
    private long trackId;
}
