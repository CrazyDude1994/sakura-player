package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.events.PlayerEvent;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.File;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.OnClick;

public class PlayerFragment extends BaseFragment implements DiscreteSeekBar.OnProgressChangeListener {

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

    @Bind(R.id.fragment_player_seekbar)
    DiscreteSeekBar mDiscreteSeekBar;

    private TrackModel mCurrentTrack;
    private Callbacks.OnPlayerListener mOnPlayerListener;
    private boolean mIsInTouchMode = false;
    private int mSeekProgress = 0;
    private boolean mIsShuffled = false;
    private boolean mIsRepeated = false;

    public static PlayerFragment newInstance() {
        PlayerFragment playerFragment = new PlayerFragment();
        return playerFragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_player;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        mDiscreteSeekBar.setOnProgressChangeListener(this);
        if (mCurrentTrack != null) {
            setPlaying();
            mArtistName.setText(mCurrentTrack.getArtistName());
            mSongName.setText(mCurrentTrack.getTrackName());

            if (mCurrentTrack.getAlbumArtPath() != null) {
                File albumArtFile = new File(mCurrentTrack.getAlbumArtPath());
                Picasso.with(getContext())
                        .load(albumArtFile)
                        .error(TextDrawable.builder().buildRect(mCurrentTrack.getTrackName(), mAccentColor))
                        .noPlaceholder()
                        .into(mAlbumArt);
            } else {
                mAlbumArt.setImageDrawable(TextDrawable.builder().buildRect(mCurrentTrack.getTrackName().substring(0, 1), mAccentColor));
            }
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
                setShuffleMode(mIsShuffled);
                break;
            case R.id.fragment_player_button_repeat:
                mIsRepeated = !mIsRepeated;
                mOnPlayerListener.onSwitchRepeat(mIsRepeated);
                setRepeatMode(mIsRepeated);
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnPlayerListener = (Callbacks.OnPlayerListener) activity;
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
        setData(event.getTrackModel());
        switch (event.getAction()) {
            case PLAY:
                setPlaying();
                break;
            case STOP:
                setStopped();
                break;
            case PAUSE:
                setPaused();
                break;
            case NEXT:
                break;
            case PREV:
                break;
            case RESUME:
                setPlaying();
                break;
        }
    }

    @Subscribe
    public void onPlayerSeekEvent(PlayerEvent.PlayerSeekEvent event) {
        if (!mIsInTouchMode) {
            setProgress(event.getProgress(), event.getDuration());
        }
    }

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
        mCurrentTrack = data;
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

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}