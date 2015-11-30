package com.crazydude.sakuraplayer.providers;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.squareup.otto.Bus;

import javax.inject.Named;

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

    public Cursor getTracklistCursor() {
        String selection = MediaStore.Audio.Media.IS_MUSIC + " = ?";

        String[] selectionArgs = {
                "1"
        };

        return mContext.getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, selection, selectionArgs, null);
    }
}
