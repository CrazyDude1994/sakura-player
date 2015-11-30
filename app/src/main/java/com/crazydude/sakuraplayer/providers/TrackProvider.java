package com.crazydude.sakuraplayer.providers;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.squareup.otto.Bus;

import javax.inject.Named;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnArtistsLoadedListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnTracksLoadedListener;

/**
 * Created by Crazy on 16.05.2015.
 */
public class TrackProvider {

    private MusicLibraryManager mMusicLibraryManager;
    private Bus mBus;
    private Context mContext;

    public TrackProvider(MusicLibraryManager mMusicLibraryManager, Bus mBus, @Named("Application") Context context) {
        this.mMusicLibraryManager = mMusicLibraryManager;
        this.mBus = mBus;
        this.mContext = context;
    }

    public Cursor getArtistCursor() {
        return mContext.getContentResolver()
                .query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null, null, null);
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
