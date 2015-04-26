package com.crazydude.sakuraplayer.mvp.presenters;

import android.net.Uri;
import android.view.View;

/**
 * Created by CrazyDude on 09.04.2015.
 */
public interface IPlayerPresenter {

    public void playMusic(Uri file);
    public void pause();
    public void continuePlay();
    public void nextTrack();
    public void prevTrack();
    public void onStop();
    public void onClick(View view);
}
