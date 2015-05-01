package com.crazydude.sakuraplayer.mvp.presenters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.managers.PlayerBinder;
import com.crazydude.sakuraplayer.mvp.models.interfaces.PlayerModel;
import com.crazydude.sakuraplayer.mvp.models.PlayerModelImpl;
import com.crazydude.sakuraplayer.mvp.presenters.interfaces.PlayerPresenter;
import com.crazydude.sakuraplayer.mvp.views.interfaces.PlayerView;
import com.crazydude.sakuraplayer.services.PlayerService_;

/**
 * Created by CrazyDude on 09.04.2015.
 */
public class PlayerPresenterImpl extends BasePresenterImpl<PlayerView, PlayerModel> implements PlayerPresenter, ServiceConnection {

    private PlayerBinder mBinder;

    public PlayerPresenterImpl(Context context, PlayerView presenterView, PlayerModel model) {
        super(context, presenterView, model);
        Intent intent = new Intent(context, PlayerService_.class);
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mBinder = (PlayerBinder) service;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mBinder = null;
    }

    public void onStop() {
        if (mBinder != null) {
            mContext.unbindService(this);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_player_button_play:
                if (mBinder.isPlaying()) {
                    mBinder.pause();
                } else {
                    mBinder.resume();
                }
        }
    }

    @Override
    public void playMusic(Uri file) {
        if (mBinder != null) {
            mBinder.play(file.getPath());
        }
    }

    @Override
    public void pause() {
        if (mBinder != null) {
            mBinder.pause();
        }
    }

    @Override
    public void continuePlay() {
        if (mBinder != null) {
            mBinder.resume();
        }
    }

    @Override
    public void nextTrack() {
    }

    @Override
    public void prevTrack() {

    }
}
