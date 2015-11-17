package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.events.PlayerEvent;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.views.fragments.PlayerView;
import com.squareup.otto.Subscribe;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerFragment extends BaseFragment implements DiscreteSeekBar.OnProgressChangeListener {

    @Inject
    PlayerView mPlayerView;

    @Inject
    TrackProvider mTrackProvider;

    @Bind(R.id.fragment_player_seekbar)
    DiscreteSeekBar mDiscreteSeekBar;

    private TrackModel mCurrentTrack;
    private Callbacks.OnPlayerListener mOnPlayerListener;
    private boolean mIsInTouchMode = false;
    private int mSeekProgress = 0;
    private boolean mIsShuffled = false;
    private boolean mIsRepeated = false;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_player;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        ButterKnife.bind(this, rootView);
        ButterKnife.bind(mPlayerView, rootView);
        mDiscreteSeekBar.setOnProgressChangeListener(this);
        if (mCurrentTrack != null) {
            mPlayerView.setPlaying();
            mPlayerView.setData(mCurrentTrack);
        }
    }

    @OnClick({R.id.fragment_player_button_play, R.id.fragment_player_button_next,
            R.id.fragment_player_button_prev, R.id.fragment_player_button_shuffle,
            R.id.fragment_player_button_repeat})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_player_button_play:
                mOnPlayerListener.onPauseOrResume();
                break;
            case R.id.fragment_player_button_next:
                mOnPlayerListener.onNext();
                break;
            case R.id.fragment_player_button_prev:
                mOnPlayerListener.onPrevious();
                break;
            case R.id.fragment_player_button_shuffle:
                mIsShuffled = !mIsShuffled;
                mOnPlayerListener.onSwitchShuffle(mIsShuffled);
                mPlayerView.setShuffleMode(mIsShuffled);
                break;
            case R.id.fragment_player_button_repeat:
                mIsRepeated = !mIsRepeated;
                mOnPlayerListener.onSwitchRepeat(mIsRepeated);
                mPlayerView.setRepeatMode(mIsRepeated);
                break;
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

    @Subscribe
    public void onPlayerPlaybackEvent(PlayerEvent.PlayerPlaybackEvent event) {
        mPlayerView.setData(event.getTrackModel());
        switch (event.getAction()) {
            case PLAY:
                mPlayerView.setPlaying();
                break;
            case STOP:
                mPlayerView.setStopped();
                break;
            case PAUSE:
                mPlayerView.setPaused();
                break;
            case NEXT:
                break;
            case PREV:
                break;
            case RESUME:
                mPlayerView.setPlaying();
                break;
        }
    }

    @Subscribe
    public void onPlayerSeekEvent(PlayerEvent.PlayerSeekEvent event) {
        if (!mIsInTouchMode) {
            mPlayerView.setProgress(event.getProgress(), event.getDuration());
        }
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }

    public static PlayerFragment newInstance() {
        PlayerFragment playerFragment = new PlayerFragment();
        return playerFragment;
    }
}