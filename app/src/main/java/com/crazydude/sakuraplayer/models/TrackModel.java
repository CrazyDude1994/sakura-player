package com.crazydude.sakuraplayer.models;

/**
 * Created by Crazy on 26.04.2015.
 */
public class TrackModel {
    private ArtistModel mArtist;
    private String mTrackName;
    private String mAlbumName;

    public ArtistModel getArtist() {
        return mArtist;
    }

    public void setArtist(ArtistModel mArtist) {
        this.mArtist = mArtist;
    }

    public String getTrackName() {
        return mTrackName;
    }

    public void setTrackName(String mTrackName) {
        this.mTrackName = mTrackName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public void setAlbumName(String mAlbumName) {
        this.mAlbumName = mAlbumName;
    }
}
