package com.crazydude.sakuraplayer.services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.managers.PlayerBinder;

import org.androidannotations.annotations.EService;

import java.io.IOException;

/**
 * Created by CrazyDude on 09.04.2015.
 */
@EService
public class PlayerService extends Service implements MediaPlayer.OnErrorListener,
        MediaPlayer.OnPreparedListener {

    private static final String TAG = PlayerService.class.getSimpleName();

    public static final String ACTION_PLAY = "com.crazydude.sakuraplayer.PLAY";
    public static final String EXTRA_PATH = "extra_path";

    private MediaPlayer mMediaPlayer;
    private PlayerBinder mBinder = new PlayerBinder(this);

    @Override
    public IBinder onBind(Intent intent) {
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
        if (intent != null) {
            switch (intent.getAction()) {
                case ACTION_PLAY:
                    String path = intent.getStringExtra(EXTRA_PATH);
                    playMusic(path);
                    break;
            }
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("HELLO")
                .setContentText("OMG");
        builder.build();
        return START_STICKY;
    }

    private void setupPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
    }

    public void playMusic(String path) {
        setupPlayer();
        try {
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepareAsync();
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
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    public void pauseMusic() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    public void resumeMusic() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
        }
    }
}
