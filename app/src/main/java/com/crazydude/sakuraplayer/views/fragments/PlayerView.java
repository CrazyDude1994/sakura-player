package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.squareup.picasso.Picasso;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.File;

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

    @ViewById(R.id.fragment_player_image)
    ImageView mAlbumArt;

    @RootContext
    Context mContext;


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
            File file = new File(data.getAlbum().getAlbumArtPath());
            Picasso.with(mContext).load(file).into(mAlbumArt);
        } else {
            mArtistName.setText("");
            mSongName.setText("");
            mAlbumArt.setImageDrawable(null);
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
