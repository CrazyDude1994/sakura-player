package com.crazydude.sakuraplayer.gui.views;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.DataView;
import com.crazydude.sakuraplayer.models.TrackModel;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Crazy on 16.05.2015.
 */
public class TrackView extends RelativeLayout implements DataView<TrackModel> {

    @Bind(R.id.view_track_artist)
    TextView mTrackArtist;

    @Bind(R.id.view_track_song_name)
    TextView mSongName;

    @Bind(R.id.view_track_song_duration)
    TextView mTrackSongDuration;

    @Bind(R.id.view_track_favorite_checkbox)
    CheckBox mFavoriteCheckbox;

    public TrackView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_track, this);
        ButterKnife.bind(this);
    }

    public void setContent(TrackModel data) {
        if (data.getArtist() != null) {
            mTrackArtist.setText(data.getArtist().getArtistName());
        }
        mSongName.setText(data.getTrackName());
        //mTrackSongDuration.setText();
    }
}
