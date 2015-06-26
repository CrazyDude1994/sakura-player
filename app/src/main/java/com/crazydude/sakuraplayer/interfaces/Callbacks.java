package com.crazydude.sakuraplayer.interfaces;

import android.view.View;

import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.models.net.ErrorResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crazy on 16.05.2015.
 */
public interface Callbacks {

    interface OnAfterSplashScreenListener {
        void onAfterSplashScreen();
    }

    interface OnTracksLoadedListener {
        void onTrackLoaded(TrackModel trackModel);
        void onTrackLoaded(ArrayList<TrackModel> tracks);
    }

    interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    interface OnArtistsLoadedListener {
        void onArtistsLoaded(ArrayList<ArtistModel> artists);
    }

    interface OnLastfmTutorialCompletedListener {
        void onTutorialCompleted(boolean isLoginToLastfm);
    }

    interface OnLastfmLoginListener {
        void onLastfmLogin(String login, String password);

        void onLastfmRegister();
    }

    interface OnResponseListener<T extends ErrorResponse> {
        void onSuccess(T response);

        void onLastfmError(String message, Integer code);

        void onNetworkError(String message);
    }

    interface OnSelectedLastfmArtistListener {
        void onSelecteLastfmArtist(String name, String mbid);
    }

    interface OnSelectedTrackListener {
        void onSelectedTrack(TrackModel track);
    }

    interface OnSelectedArtistListener {
        void onSelectedArtist(ArtistModel artist);
    }

    interface OnPlayerListener {
        void onPauseOrResume();
        void onNext();
        void onPrevious();
        void onSeek(int progress);
        void onSetPlaylist(PlaylistModel playlist);
        void onSwitchShuffle(boolean isShuffle);
        void onSwitchRepeat(boolean isRepeat);
    }
}
