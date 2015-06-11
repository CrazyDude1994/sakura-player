package com.crazydude.sakuraplayer.gui.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.managers.PlayerBinder;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.services.PlayerService;
import com.crazydude.sakuraplayer.services.PlayerService_;
import com.crazydude.sakuraplayer.views.fragments.PlayerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_player)
public class PlayerFragment extends BaseFragment implements ServiceConnection {

    @Bean
    PlayerView mPlayerView;

    private PlayerBinder mBinder;
    private TrackModel mCurrentTrack;

    @AfterViews
    void init() {
        if (mCurrentTrack != null) {
            playMusic(mCurrentTrack);
        }
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

    public void setData(TrackModel data) {
        mCurrentTrack = data;
    }

    public void playMusic(TrackModel model) {
        if (mBinder == null) {
            Intent intent = new Intent(getActivity(), PlayerService_.class);
            if (model != null) {
                mPlayerView.setData(model);
                intent.setAction(PlayerService.ACTION_PLAY);
                intent.putExtra(PlayerService.EXTRA_PATH, model.getTrackPath());
            }
            getActivity().bindService(intent, this, Context.BIND_AUTO_CREATE);
        } else {
            mBinder.play(model.getTrackPath());
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

    public boolean isPlaying() {
        if (mBinder != null) {
            return mBinder.isPlaying();
        }
        return false;
    }
}