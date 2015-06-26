package com.crazydude.sakuraplayer.events;

import com.crazydude.sakuraplayer.models.TrackModel;

/**
 * Created by kartavtsev.s on 26.06.2015.
 */
public class PlayerEvent {



    public static class PlayerPlaybackEvent {

        public enum PlaybackAction {
            PLAY, STOP, PAUSE, NEXT, PREV, RESUME, SEEK;
        }

        private TrackModel trackModel;
        private PlaybackAction action;

        public PlaybackAction getAction() {
            return action;
        }

        public void setAction(PlaybackAction action) {
            this.action = action;
        }

        public TrackModel getTrackModel() {
            return trackModel;
        }

        public void setTrackModel(TrackModel trackModel) {
            this.trackModel = trackModel;
        }
    }

    public static class PlayerSeekEvent extends PlayerPlaybackEvent {

        private int progress;
        private int duration;

        public PlayerSeekEvent() {
            this.setAction(PlaybackAction.SEEK);
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }
}
