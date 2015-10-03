package com.crazydude.sakuraplayer.providers;

import com.activeandroid.query.Select;
import com.crazydude.sakuraplayer.events.UpdateLibraryCompletedEvent;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.squareup.otto.Bus;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnArtistsLoadedListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnTracksLoadedListener;

/**
 * Created by Crazy on 16.05.2015.
 */
public class TrackProvider {

    @Inject
    MusicLibraryManager mMusicLibraryManager;

    @Inject
    Bus mBus;

    public void updateMusicDatabase() {
        mMusicLibraryManager.generateDatabase();
        mBus.post(new UpdateLibraryCompletedEvent());
    }

    public void loadAllTracks(OnTracksLoadedListener listener) {
        ArrayList<TrackModel> models = ((ArrayList) new Select().from(TrackModel.class).execute());
        if (listener != null && models != null) {
            listener.onTrackLoaded(models);
        }
    }

    public void loadAllArtists(OnArtistsLoadedListener listener) {
        ArrayList<ArtistModel> models = ((ArrayList) new Select().from(ArtistModel.class).execute());
        if (listener != null && models != null) {
            listener.onArtistsLoaded(models);
        }
    }

    public void loadAllTracksByArtist(String artistName, OnTracksLoadedListener listener) {
        ArtistModel artistModel = new Select()
                .from(ArtistModel.class)
                .where("Name = ?", artistName)
                .executeSingle();
        ArrayList<TrackModel> models = ((ArrayList) new Select()
                .from(TrackModel.class)
                .where("Artist = ?", artistModel.getId())
                .execute());
        if (listener != null && models != null) {
            listener.onTrackLoaded(models);
        }
    }

    public void loadTrackById(long trackId, OnTracksLoadedListener listener) {
        TrackModel model = new Select()
                .from(TrackModel.class)
                .where("Track_ID = ?", trackId)
                .executeSingle();
        if (listener != null && model != null) {
            listener.onTrackLoaded(model);
        }
    }
}
