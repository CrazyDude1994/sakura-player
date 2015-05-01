package com.crazydude.sakuraplayer.mvp.models;

import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.mvp.events.OnLoadedTracks;
import com.crazydude.sakuraplayer.mvp.models.interfaces.TrackListModel;
import com.crazydude.sakuraplayer.mvp.presenters.interfaces.TracklistPresenter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by Crazy on 26.04.2015.
 */
@EBean
public class TrackListModelImpl implements TrackListModel {

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    @Override
    public void loadAllTracks() {
        ArrayList<String> trackList = mMusicLibraryManager.getAllTracks();
        OnLoadedTracks event = new OnLoadedTracks();
        event.trackList = trackList;
        EventBus.getDefault().post(event);
    }
}
