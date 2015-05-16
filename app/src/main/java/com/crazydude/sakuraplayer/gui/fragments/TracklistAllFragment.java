package com.crazydude.sakuraplayer.gui.fragments;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.views.fragments.TracklistAllFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import java.util.List;

/**
 * Created by CrazyDude on 13.04.2015.
 */
@EFragment(R.layout.fragment_tracklist_alltracks)
public class TracklistAllFragment extends BaseFragment implements Callbacks.OnTracksLoadedListener {

    @Bean
    TracklistAllFragmentView mTracklistAllFragmentView;

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    @Bean
    TrackProvider mTrackProvider;

    @AfterViews
    void initViews() {
        mTrackProvider.loadAllTracks(this);
    }

    @UiThread
    @Override
    public void onTrackLoaded(List<TrackModel> tracks) {
        mTracklistAllFragmentView.setTrackList(tracks);
    }

//
//    private TracklistAllView mTracklistAllView;
//
//    public void setView(TracklistAllView view) {
//        this.mTracklistAllView = view;
//    }
//
//    @AfterInject
//    void init() {
//        EventBus.getDefault().register(this);
//    }
//
//    public void onEvent(OnLoadedTracks event) {
//        Log.d("TRACKS", "LOADED TRACKS");
//    }
//
//    @Override
//    public void loadAllTracks() {
//        mModel.loadAllTracks();
//    }
//
//    @Override
//    public void onResume() {
//        loadAllTracks();
//    }
//
//    @Override
//    public void onPause() {
//
//    }
}
