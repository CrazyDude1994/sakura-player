package com.crazydude.sakuraplayer.providers;

import android.content.Context;

import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.squareup.otto.Bus;

import javax.inject.Named;

/**
 * Created by Crazy on 16.05.2015.
 */
@Deprecated
public class TrackProvider {

    private MusicLibraryManager mMusicLibraryManager;
    private Bus mBus;
    private Context mContext;

    public TrackProvider(MusicLibraryManager mMusicLibraryManager, Bus mBus, @Named("Application") Context context) {
        this.mMusicLibraryManager = mMusicLibraryManager;
        this.mBus = mBus;
        this.mContext = context;
    }
}
