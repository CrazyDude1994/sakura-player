package com.crazydude.sakuraplayer.gui.views;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.DataView;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Crazy on 16.05.2015.
 */
@EViewGroup(R.layout.view_track)
public class TrackView extends RelativeLayout implements DataView<TrackModel> {

    @ViewById(R.id.view_track_artist)
    TextView mTrackArtist;

    @ViewById(R.id.view_track_song_name)
    TextView mSongName;

    @ViewById(R.id.view_track_song_duration)
    TextView mTrackSongDuration;

    @ViewById(R.id.view_track_favorite_checkbox)
    CheckBox mFavoriteCheckbox;

    public TrackView(Context context) {
        super(context);
    }

    public void setContent(TrackModel data) {
        if (data.getArtist() != null) {
            mTrackArtist.setText(data.getArtist().getArtistName());
        }
        mSongName.setText(data.getTrackName());
        //mTrackSongDuration.setText();
    }
}
