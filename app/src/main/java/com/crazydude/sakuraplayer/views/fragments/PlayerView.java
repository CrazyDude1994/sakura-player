package com.crazydude.sakuraplayer.views.fragments;

import android.widget.ImageButton;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Crazy on 15.05.2015.
 */
@EBean
public class PlayerView {

    @ViewById(R.id.fragment_player_seekbar)
    DiscreteSeekBar mDiscreteSeekBar;

    @ViewById(R.id.fragment_player_artist_name)
    TextView mArtistName;

    @ViewById(R.id.fragment_player_song_name)
    TextView mSongName;

    @ViewById(R.id.fragment_player_button_play)
    ImageButton mPlayButton;

    @ViewById(R.id.fragment_player_button_repeat)
    ImageButton mRepeatButton;

    @ViewById(R.id.fragment_player_button_shuffle)
    ImageButton mShuffleButton;

    @AfterViews
    void initViews() {
    }

    @UiThread
    public void setPlaying() {
        mPlayButton.setImageResource(R.drawable.ic_pause);
    }

    @UiThread
    public void setPaused() {
        mPlayButton.setImageResource(R.drawable.ic_play_arrow);
    }

    @UiThread
    public void setRepeatMode(boolean isRepeat) {
        if (isRepeat) {
            mRepeatButton.setImageResource(R.drawable.ic_repeat_activated);
        } else {
            mRepeatButton.setImageResource(R.drawable.ic_repeat);
        }
    }

    @UiThread
    public void setShuffleMode(boolean isShuffle) {
        if (isShuffle) {
            mShuffleButton.setImageResource(R.drawable.ic_shuffle_activated);
        } else {
            mShuffleButton.setImageResource(R.drawable.ic_shuffle);
        }

    }

    @UiThread
    public void setData(TrackModel data) {
        if (data != null) {
            mArtistName.setText(data.getArtist().getArtistName());
            mSongName.setText(data.getTrackName());
        } else {
            mArtistName.setText("");
            mSongName.setText("");
        }
    }

    @UiThread
    public void setProgress(int progress, int duration) {
        mDiscreteSeekBar.setMax(duration);
        mDiscreteSeekBar.setProgress(progress);
    }

    @UiThread
    public void setStopped() {
        mDiscreteSeekBar.setProgress(0);
        mDiscreteSeekBar.setMax(0);
        setPaused();
        setData(null);
    }
}
