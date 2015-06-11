package com.crazydude.sakuraplayer.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Crazy on 26.04.2015.
 */
@Table(name = "Tracks")
public class TrackModel extends Model {

    @Column(name = "Artist")
    private ArtistModel mArtist;
    @Column(name = "SongName")
    private String mTrackName;
    @Column(name = "AlbumName")
    private String mAlbumName;
    @Column(name = "Path")
    private String mTrackPath;

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

    public void setTrackPath(String trackPath) {
        this.mTrackPath = trackPath;
    }

    public String getTrackPath() {
        return mTrackPath;
    }
}
