package com.crazydude.sakuraplayer.mvp.presenters;

import android.media.MediaPlayer;
import android.net.Uri;

import com.crazydude.sakuraplayer.mvp.views.PlayerView;

/**
 * Created by CrazyDude on 09.04.2015.
 */
public class PlayerPresenter implements IPlayerPresenter {

    private PlayerView mPlayerView;

    PlayerPresenter(PlayerView playerView) {
        this.mPlayerView = playerView;
    }

    @Override
    public void playMusic(Uri file) {

    }
}
