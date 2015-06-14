package com.crazydude.sakuraplayer.providers;

import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.HashSet;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnArtistsLoadedListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnTracksLoadedListener;

/**
 * Created by Crazy on 16.05.2015.
 */
@EBean
public class TrackProvider {

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    @Background
    public void loadAllTracks(OnTracksLoadedListener listener) {
        ArrayList<TrackModel> models = mMusicLibraryManager.getAllTracks();
        if (listener != null && models != null) {
            listener.onTrackLoaded(models);
        }
    }

    @Background
    public void loadAllArtists(OnArtistsLoadedListener listener) {
        ArrayList<ArtistModel> models = mMusicLibraryManager.getArtistList();
        if (listener != null && models != null) {
            listener.onArtistsLoaded(models);
        }
    }

    @Background
    public void loadAllTracksByArtist(String artistName, OnTracksLoadedListener listener) {
        ArrayList<TrackModel> models = mMusicLibraryManager.getTracksByArtist(artistName);
        if (listener != null && models != null) {
            listener.onTrackLoaded(models);
        }
    }
}
