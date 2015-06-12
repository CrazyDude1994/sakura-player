package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.services.PlayerService;
import com.crazydude.sakuraplayer.views.fragments.PlayerView;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_player)
public class PlayerFragment extends BaseFragment implements DiscreteSeekBar.OnProgressChangeListener {

    @Bean
    PlayerView mPlayerView;

    @ViewById(R.id.fragment_player_seekbar)
    DiscreteSeekBar mDiscreteSeekBar;

    private TrackModel mCurrentTrack;
    private Callbacks.OnPlayerListener mOnPlayerListener;
    private PlayerBroadcastReceiver mPlayerBroadcastReceiver;
    private boolean mIsInTouchMode = false;
    private int mSeekProgress = 0;

    @AfterViews
    void init() {
        mDiscreteSeekBar.setOnProgressChangeListener(this);
        if (mCurrentTrack != null) {
            mPlayerView.setPlaying();
            mPlayerView.setData(mCurrentTrack);
        }
        if (mPlayerBroadcastReceiver == null) {
            mPlayerBroadcastReceiver = new PlayerBroadcastReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PlayerService.ACTION_PLAY);
        intentFilter.addAction(PlayerService.ACTION_PAUSE);
        intentFilter.addAction(PlayerService.ACTION_RESUME);
        intentFilter.addAction(PlayerService.ACTION_SEEK);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mPlayerBroadcastReceiver, intentFilter);
    }

    @Click({R.id.fragment_player_button_play, R.id.fragment_player_button_next,
            R.id.fragment_player_button_prev})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_player_button_play:
                mOnPlayerListener.onPauseOrResume();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnPlayerListener = (Callbacks.OnPlayerListener) activity;
    }

    public void setData(TrackModel data) {
        mCurrentTrack = data;
    }

    @Override
    public void onProgressChanged(DiscreteSeekBar discreteSeekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mSeekProgress = progress;
        }
    }

    @Override
    public void onStartTrackingTouch(DiscreteSeekBar discreteSeekBar) {
        mIsInTouchMode = true;
    }

    @Override
    public void onStopTrackingTouch(DiscreteSeekBar discreteSeekBar) {
        mIsInTouchMode = false;
        mOnPlayerListener.onSeek(mSeekProgress);
    }

    private class PlayerBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case PlayerService.ACTION_PLAY:
                    mPlayerView.setPlaying();
                    break;
                case PlayerService.ACTION_PAUSE:
                    mPlayerView.setPaused();
                    break;
                case PlayerService.ACTION_RESUME:
                    mPlayerView.setPlaying();
                    break;
                case PlayerService.ACTION_SEEK:
                    int duration = intent.getIntExtra(PlayerService.EXTRA_DURATION, 0);
                    int progress = intent.getIntExtra(PlayerService.EXTRA_PROGRESS, 0);
                    if (!mIsInTouchMode) {
                        mPlayerView.setProgress(progress, duration);
                    }
            }
        }
    }
}