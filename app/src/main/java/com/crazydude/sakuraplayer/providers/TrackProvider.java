package com.crazydude.sakuraplayer.providers;

import com.crazydude.sakuraplayer.events.TracklistUpdateCompletedEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryCompletedEvent;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.squareup.otto.Bus;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnArtistsLoadedListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnTracksLoadedListener;

/**
 * Created by Crazy on 16.05.2015.
 */
public class TrackProvider {

    private MusicLibraryManager mMusicLibraryManager;
    private Bus mBus;

    public TrackProvider(MusicLibraryManager mMusicLibraryManager, Bus mBus) {
        this.mMusicLibraryManager = mMusicLibraryManager;
        this.mBus = mBus;
    }

    public void updateMusicDatabase() {
        mMusicLibraryManager.generateDatabase();
    }

    public void loadAllTracks(OnTracksLoadedListener listener) {
    }

    public void loadAllArtists(OnArtistsLoadedListener listener) {
    }

    public void loadAllTracksByArtist(String artistName, OnTracksLoadedListener listener) {
    }

    public void loadTrackById(long trackId, OnTracksLoadedListener listener) {
    }
}
