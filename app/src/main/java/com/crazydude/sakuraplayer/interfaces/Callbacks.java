package com.crazydude.sakuraplayer.interfaces;

import com.crazydude.sakuraplayer.models.TrackModel;

import java.util.List;

/**
 * Created by Crazy on 16.05.2015.
 */
public interface Callbacks {

    public interface OnAfterSplashScreenListener {
        public void onAfterSplashScreen();
    }

    public interface OnTracksLoadedListener {
        public void onTrackLoaded(List<TrackModel> tracks);
    }
}
