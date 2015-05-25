package com.crazydude.sakuraplayer.gui.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.managers.PlayerBinder;
import com.crazydude.sakuraplayer.services.PlayerService_;
import com.crazydude.sakuraplayer.views.fragments.PlayerView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

@EFragment(R.layout.fragment_player)
public class PlayerFragment extends BaseFragment implements ServiceConnection {

    private PlayerBinder mBinder;
    private PlayerView mPlayerView;

    @AfterInject
    void init() {
        Intent intent = new Intent(getActivity(), PlayerService_.class);
        getActivity().bindService(intent, this, Context.BIND_AUTO_CREATE);

        mPlayerView = new PlayerView();
    }

    @Click({R.id.fragment_player_button_play, R.id.fragment_player_button_next,
            R.id.fragment_player_button_prev})
    void onClick(View v) {
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
    public void onServiceConnected(ComponentName name, IBinder service) {
        mBinder = (PlayerBinder) service;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mBinder = null;
    }

    public void onStop() {
        super.onStop();
        if (mBinder != null) {
            getActivity().unbindService(this);
        }
    }

    public void playMusic(Uri file) {
        if (mBinder != null) {
            mBinder.play(file.getPath());
        }
    }

    public void pause() {
        if (mBinder != null) {
            mBinder.pause();
        }
    }

    public void continuePlay() {
        if (mBinder != null) {
            mBinder.resume();
        }
    }

    public void nextTrack() {
    }

    public void prevTrack() {

    }
}