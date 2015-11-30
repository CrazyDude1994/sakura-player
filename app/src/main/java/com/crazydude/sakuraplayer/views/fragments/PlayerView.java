package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.squareup.picasso.Picasso;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.File;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindColor;

/**
 * Created by Crazy on 15.05.2015.
 */
public class PlayerView {

    @Bind(R.id.fragment_player_seekbar)
    DiscreteSeekBar mDiscreteSeekBar;

    @Bind(R.id.fragment_player_artist_name)
    TextView mArtistName;

    @Bind(R.id.fragment_player_song_name)
    TextView mSongName;

    @Bind(R.id.fragment_player_button_play)
    ImageButton mPlayButton;

    @Bind(R.id.fragment_player_button_repeat)
    ImageButton mRepeatButton;

    @Bind(R.id.fragment_player_button_shuffle)
    ImageButton mShuffleButton;

    @Bind(R.id.fragment_player_image)
    ImageView mAlbumArt;

    @BindColor(R.color.accent)
    int mAccentColor;

    @Inject
    Context mContext;

    public void setPlaying() {
        mPlayButton.setImageResource(R.drawable.ic_pause);
    }

    public void setPaused() {
        mPlayButton.setImageResource(R.drawable.ic_play_arrow);
    }

    public void setRepeatMode(boolean isRepeat) {
        if (isRepeat) {
            mRepeatButton.setImageResource(R.drawable.ic_repeat_activated);
        } else {
            mRepeatButton.setImageResource(R.drawable.ic_repeat);
        }
    }

    public void setShuffleMode(boolean isShuffle) {
        if (isShuffle) {
            mShuffleButton.setImageResource(R.drawable.ic_shuffle_activated);
        } else {
            mShuffleButton.setImageResource(R.drawable.ic_shuffle);
        }

    }

    public void setData(TrackModel data) {
        mArtistName.setText(data.getArtist().getArtistName());
        mSongName.setText(data.getTrackName());

/*        if (data.getAlbum() != null && data.getAlbum().getAlbumArtPath() != null) {
            File albumArtFile = new File(data.getAlbum().getAlbumArtPath());
            Picasso.with(mContext)
                    .load(albumArtFile)
                    .error(TextDrawable.builder().buildRect(data.getTrackName(), mAccentColor))
                    .noPlaceholder()
                    .into(mAlbumArt);
        } else {
            mAlbumArt.setImageDrawable(TextDrawable.builder().buildRect(data.getTrackName().substring(0, 1), mAccentColor));
        }*/
    }

    public void setProgress(int progress, int duration) {
        mDiscreteSeekBar.setMax(duration);
        mDiscreteSeekBar.setProgress(progress);
    }

    public void setStopped() {
        mDiscreteSeekBar.setProgress(0);
        mDiscreteSeekBar.setMax(0);
        setPaused();
        setData(null);
    }
}
