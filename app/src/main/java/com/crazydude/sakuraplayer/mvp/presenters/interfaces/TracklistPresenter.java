package com.crazydude.sakuraplayer.mvp.presenters.interfaces;

import android.content.Context;

import com.crazydude.sakuraplayer.mvp.views.interfaces.TracklistAllView;

/**
 * Created by Crazy on 26.04.2015.
 */
public interface TracklistPresenter {

    public void loadAllTracks();
    public void onResume();
    public void onPause();

}
