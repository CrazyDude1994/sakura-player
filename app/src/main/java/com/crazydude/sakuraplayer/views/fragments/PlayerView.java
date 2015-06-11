package com.crazydude.sakuraplayer.views.fragments;

import android.widget.Button;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;
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

    @AfterViews
    void initViews() {
    }

    @UiThread
    public void setData(TrackModel data) {
        mArtistName.setText(data.getArtist().getArtistName());
        mSongName.setText(data.getTrackName());
    }
}
