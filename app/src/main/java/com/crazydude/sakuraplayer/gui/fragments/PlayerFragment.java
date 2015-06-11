package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.services.PlayerService;
import com.crazydude.sakuraplayer.views.fragments.PlayerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_player)
public class PlayerFragment extends BaseFragment {

    @Bean
    PlayerView mPlayerView;

    private TrackModel mCurrentTrack;
    private Callbacks.OnPlayerListener mOnPlayerListener;

    @AfterViews
    void init() {
        if (mCurrentTrack != null) {
            mPlayerView.setData(mCurrentTrack);
        }
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
}