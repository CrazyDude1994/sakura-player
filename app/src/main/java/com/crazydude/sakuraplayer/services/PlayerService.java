package com.crazydude.sakuraplayer.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import org.androidannotations.annotations.EService;

import static com.crazydude.sakuraplayer.services.PlayerService.ACTION_PLAY;

/**
 * Created by CrazyDude on 09.04.2015.
 */
@EService
public class PlayerService extends Service implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {

    public static final String ACTION_PLAY = "com.crazydude.sakuraplayer.PLAY";

    private MediaPlayer mMediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       switch (intent.getAction()) {
           case ACTION_PLAY:
               mMediaPlayer.start();

       }
        return super.onStartCommand(intent, flags, startId);
    }

    private void playMusic() {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
