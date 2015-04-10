package com.crazydude.sakuraplayer.mvp.presenters;

import android.net.Uri;

import com.crazydude.sakuraplayer.mvp.models.IPlayerModel;
import com.crazydude.sakuraplayer.mvp.models.PlayerModel;
import com.crazydude.sakuraplayer.mvp.views.PlayerView;

/**
 * Created by CrazyDude on 09.04.2015.
 */
public class PlayerPresenter implements IPlayerPresenter {

    private PlayerView mPlayerView;
    private IPlayerModel mPlayerModel;

    public PlayerPresenter(PlayerView playerView) {
        this.mPlayerView = playerView;
        this.mPlayerModel = new PlayerModel(this);
    }

    @Override
    public void playMusic(Uri file) {

    }
}
