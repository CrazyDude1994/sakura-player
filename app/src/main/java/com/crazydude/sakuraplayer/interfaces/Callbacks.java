package com.crazydude.sakuraplayer.interfaces;

import android.view.View;

import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;

import java.util.ArrayList;
import java.util.HashSet;
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

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public interface OnArtistsLoadedListener {
        public void onArtistsLoaded(ArrayList<ArtistModel> artists);
    }
}
