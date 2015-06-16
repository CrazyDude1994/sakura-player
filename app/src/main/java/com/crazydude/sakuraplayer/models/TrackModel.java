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
    private ArtistModel artist;
    @Column(name = "SongName")
    private String trackName;
    @Column(name = "Path")
    private String trackPath;
    @Column(name = "Album")
    private AlbumModel album;
    @Column(name = "Track_ID")
    private long trackId;

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public AlbumModel getAlbum() {
        return album;
    }

    public void setAlbum(AlbumModel album) {
        this.album = album;
    }

    public ArtistModel getArtist() {
        return artist;
    }

    public void setArtist(ArtistModel mArtist) {
        this.artist = mArtist;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String mTrackName) {
        this.trackName = mTrackName;
    }

    public void setTrackPath(String trackPath) {
        this.trackPath = trackPath;
    }

    public String getTrackPath() {
        return trackPath;
    }
}
