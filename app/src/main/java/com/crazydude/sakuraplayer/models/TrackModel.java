package com.crazydude.sakuraplayer.models;

/**
 * Created by Crazy on 26.04.2015.
 */
public class TrackModel {

    private ArtistModel artist;
    private String trackName;
    private String trackPath;
    private AlbumModel album;
    private long trackId;

    private AlbumModel cachedAlbumModel;
    private ArtistModel cachedArtistModel;

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public AlbumModel getAlbum() {
        if (cachedAlbumModel == null) {
            cachedAlbumModel = album;
        }
        return cachedAlbumModel;
    }

    public void setAlbum(AlbumModel album) {
        this.album = album;
    }

    public ArtistModel getArtist() {
        if (cachedArtistModel == null) {
            cachedArtistModel = artist;
        }
        return cachedArtistModel;
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
