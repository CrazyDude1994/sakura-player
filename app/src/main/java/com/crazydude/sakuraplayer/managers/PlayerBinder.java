package com.crazydude.sakuraplayer.managers;

import android.os.Binder;

import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.services.PlayerService;

/**
 * Created by Serega on 10.04.2015.
 */
public class PlayerBinder extends Binder {

    PlayerService mPlayerService;

    public PlayerBinder(PlayerService playerService) {
        mPlayerService = playerService;
    }

    public void play(PlaylistModel playlist) {
        mPlayerService.playMusic(playlist);
    }

    public void play(PlaylistModel playlist, int startAt) {
        mPlayerService.playMusic(playlist, startAt);
    }

    public void stop() {
        mPlayerService.stopMusic();
    }

    public void seek(int progress) {
        mPlayerService.seekMusic(progress);
    }

    public void pause() {
        mPlayerService.pauseMusic();
    }

    public void next() {
        mPlayerService.playNext();
    }

    public void previous() {
        mPlayerService.playPrevious();
    }

    public void setRepeatMode(boolean isRepeat) {
        mPlayerService.setLoopMode(isRepeat);
    }

    public void setShuffleMode(boolean isShuffle) {
        mPlayerService.setRandomMode(isShuffle);
    }

    public TrackModel getCurrentTrack() {
        return mPlayerService.getCurrentTrack();
    }

    public PlaylistModel getCurrentPlaylist() {
        return mPlayerService.getCurrentPlaylist();
    }

    public void resume() {
        mPlayerService.resumeMusic();
    }

    public boolean isPlaying() {
        return mPlayerService.isPlaying();
    }
}
