package com.crazydude.sakuraplayer.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.events.PlayerEvent;
import com.crazydude.sakuraplayer.gui.activity.HomeActivity;
import com.crazydude.sakuraplayer.managers.PlayerBinder;
import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.squareup.otto.Bus;

import java.io.IOException;
import java.util.Random;

import javax.inject.Inject;

/**
 * Created by CrazyDude on 09.04.2015.
 */
public class PlayerService extends Service implements MediaPlayer.OnErrorListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private static final String TAG = PlayerService.class.getSimpleName();

    public static final String ACTION_PLAY = "com.crazydude.sakuraplayer.PLAY";
    public static final String ACTION_PAUSE = "com.crazydude.sakuraplayer.PAUSE";
    public static final String ACTION_RESUME = "com.crazydude.sakuraplayer.RESUME";
    public static final String ACTION_SEEK = "com.crazydude.sakuraplayer.SEKK";
    public static final String ACTION_STOP = "com.crazydude.sakuraplayer.STOP";

    public static final String EXTRA_PATH = "extra_path";
    public static final String EXTRA_TRACK_ID = "extra_track_id";

    private MediaPlayer mMediaPlayer;
    private PlayerBinder mBinder;
    private static int NOTIFICATION_ID = 1337;
    private Handler mSyncSeekbar;
    private PlaylistModel mPlaylist = new PlaylistModel(null, "Current");
    private int mCurrentTrackIndex;
    private boolean mIsInRandomMode = false;

    @Inject
    Bus mBus;

    @Override
    public IBinder onBind(Intent intent) {
        if (mBinder == null) {
            mBinder = new PlayerBinder(this);
        }
        onStartCommand(intent, 0, 0);
        return mBinder;
    }

    @Override
    public void onDestroy() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_PLAY:
                    String path = intent.getStringExtra(EXTRA_PATH);
//                    playMusic();
                    break;
            }
        }
        return START_STICKY;
    }

    private void setupForegroundNotification(TrackModel model) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, HomeActivity.class), 0);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_equalizer_animated)
                .setContentTitle(model.getTrackName())
                .setContentText(model.getArtist().getArtistName())
                .setContentIntent(pendingIntent)
                .build();
        startForeground(NOTIFICATION_ID, notification);
    }

    private void stopForeground() {
        stopForeground(true);
    }

    private void setupPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
        if (mSyncSeekbar != null) {
            mSyncSeekbar = null;
        }
        mSyncSeekbar = new Handler();
        mSyncSeekbar.postDelayed(new SyncSeekbarThread(), 500);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
    }

    public void playMusic(PlaylistModel playlist) {
        mCurrentTrackIndex = 0;
        playCurrentMusic(playlist);
    }

    public void playMusic(PlaylistModel playlist, int startAt) {
        mCurrentTrackIndex = startAt;
        playCurrentMusic(playlist);
    }

    private void playCurrentMusic(PlaylistModel playlist) {
        mPlaylist = playlist;
        if (playlist.getTracks().size() > 0) {
            playMusic(playlist.getTracks().get(mCurrentTrackIndex));
        }
    }

    private void postPlaybackEvent(
            PlayerEvent.PlayerPlaybackEvent.PlaybackAction action, TrackModel trackModel) {
        PlayerEvent.PlayerPlaybackEvent playerPlaybackEvent = new PlayerEvent.
                PlayerPlaybackEvent();
        playerPlaybackEvent.setAction(action);
        playerPlaybackEvent.setTrackModel(trackModel);
        mBus.post(playerPlaybackEvent);
    }

    private void postSeekEvent(int duration, int progress, TrackModel trackModel) {
        PlayerEvent.PlayerSeekEvent seekEvent = new PlayerEvent.PlayerSeekEvent();
        seekEvent.setTrackModel(trackModel);
        seekEvent.setDuration(duration);
        seekEvent.setProgress(progress);
        mBus.post(seekEvent);
    }

    private void playMusic(TrackModel track) {
        setupPlayer();
        setupForegroundNotification(track);
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(track.getTrackPath());
            mMediaPlayer.prepareAsync();
            PlayerEvent.PlayerPlaybackEvent playerPlaybackEvent = new PlayerEvent.
                    PlayerPlaybackEvent();
            postPlaybackEvent(PlayerEvent.PlayerPlaybackEvent.PlaybackAction.PLAY, track);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
    }

    public void stopMusic() {
        if (mMediaPlayer != null) {
            postPlaybackEvent(PlayerEvent.PlayerPlaybackEvent.PlaybackAction.STOP, getCurrentTrack());
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
            stopForeground();
        }
    }

    public void pauseMusic() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            stopForeground();
            postPlaybackEvent(PlayerEvent.PlayerPlaybackEvent.PlaybackAction.PAUSE, getCurrentTrack());
        }
    }

    public void resumeMusic() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
            setupForegroundNotification(getCurrentTrack());
            postPlaybackEvent(PlayerEvent.PlayerPlaybackEvent.PlaybackAction.RESUME, getCurrentTrack());
        }
    }

    public TrackModel getCurrentTrack() {
        if (mPlaylist.getTracks() != null) {
            if (mCurrentTrackIndex < mPlaylist.getTracks().size()) {
                return mPlaylist.getTracks().get(mCurrentTrackIndex);
            }
        }
        return null;
    }

    public void seekMusic(int progress) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo(progress);
        }
    }

    public boolean isPlaying() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.isPlaying();
        } else {
            return false;
        }
    }

    public void setLoopMode(boolean isLoop) {
        if (mMediaPlayer != null) {
            mMediaPlayer.setLooping(isLoop);
        }
    }

    public void setRandomMode(boolean isInRandom) {
        mIsInRandomMode = isInRandom;
    }

    public void playNext() {
        playNextSong(false);
    }

    public void playPrevious() {
        playNextSong(true);
    }

    private void playNextSong(boolean isReverse) {
        if (mMediaPlayer != null) {
            if (!mMediaPlayer.isLooping()) {
                if (mIsInRandomMode) {
                    mCurrentTrackIndex = new Random().nextInt(mPlaylist.getTracks().size());
                } else {
                    if (isReverse) {
                        mCurrentTrackIndex--;
                        if (mCurrentTrackIndex <= 0) {
                            mCurrentTrackIndex = mPlaylist.getTracks().size() - 1;
                        }
                    } else {
                        mCurrentTrackIndex++;
                        if (mCurrentTrackIndex >= mPlaylist.getTracks().size()) {
                            mCurrentTrackIndex = 0;
                        }
                    }
                }
                playMusic(mPlaylist.getTracks().get(mCurrentTrackIndex));
            }
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        playNextSong(false);
    }

    public PlaylistModel getCurrentPlaylist() {
        return mPlaylist;
    }

    private class SyncSeekbarThread implements Runnable {
        @Override
        public void run() {
            if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                int position = mMediaPlayer.getCurrentPosition();
                int duration = mMediaPlayer.getDuration();
                postSeekEvent(duration, position, getCurrentTrack());
            }
            if (mSyncSeekbar != null) {
                mSyncSeekbar.postDelayed(this, 500);
            }
        }
    }
}
