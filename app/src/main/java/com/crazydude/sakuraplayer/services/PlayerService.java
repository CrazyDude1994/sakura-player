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
import android.support.v4.content.LocalBroadcastManager;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.activity.HomeActivity;
import com.crazydude.sakuraplayer.managers.PlayerBinder;
import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.EService;

import java.io.IOException;

/**
 * Created by CrazyDude on 09.04.2015.
 */
@EService
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
    public static final String EXTRA_SONG_NAME = "extra_songname";
    public static final String EXTRA_ARTIST_NAME = "extra_artistname";
    public static final String EXTRA_DURATION = "extra_duration";
    public static final String EXTRA_PROGRESS = "extra_progress";

    private MediaPlayer mMediaPlayer;
    private PlayerBinder mBinder;
    private static int NOTIFICATION_ID = 1337;
    private Handler mSyncSeekbar;
    private PlaylistModel mPlaylist = new PlaylistModel(null, "Current");
    private int mCurrentTrackIndex;

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
        mPlaylist = playlist;
        if (playlist.getTracks().size() > 0) {
            playMusic(playlist.getTracks().get(mCurrentTrackIndex));
        }
    }

    private void playMusic(TrackModel track) {
        setupPlayer();
        setupForegroundNotification(track);
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(track.getTrackPath());
            mMediaPlayer.prepareAsync();
            sendPlayBroadcast(track.getTrackName(), track.getArtist().getArtistName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendBroadcast(String action) {
        Intent intent = new Intent(action);
        LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);
    }

    private void sendPlayBroadcast(String songName, String artistName) {
        Intent intent = new Intent(ACTION_PLAY);
        intent.putExtra(EXTRA_SONG_NAME, songName);
        intent.putExtra(EXTRA_ARTIST_NAME, artistName);
        LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);
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
            sendBroadcast(ACTION_STOP);
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
            sendBroadcast(ACTION_PAUSE);
        }
    }

    public void resumeMusic() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
            setupForegroundNotification(getCurrentTrack());
            sendBroadcast(ACTION_RESUME);
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

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mMediaPlayer != null && !mMediaPlayer.isLooping()) {
            mCurrentTrackIndex++;
            if (mCurrentTrackIndex < mPlaylist.getTracks().size()) {
                playMusic(mPlaylist.getTracks().get(mCurrentTrackIndex));
            } else {
                stopMusic();
            }
        }
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
                Intent intent = new Intent(ACTION_SEEK);
                intent.putExtra(EXTRA_DURATION, duration);
                intent.putExtra(EXTRA_PROGRESS, position);
                LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);
            }
            if (mSyncSeekbar != null) {
                mSyncSeekbar.postDelayed(this, 500);
            }
        }
    }
}
