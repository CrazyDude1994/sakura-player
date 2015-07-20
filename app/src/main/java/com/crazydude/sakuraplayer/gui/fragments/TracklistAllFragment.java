package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.events.UpdateLibraryCompletedEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryStartedEvent;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.views.fragments.TracklistAllFragmentView;
import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;

/**
 * Created by CrazyDude on 13.04.2015.
 */
@EFragment(R.layout.fragment_tracklist_alltracks)
public class TracklistAllFragment extends BaseFragment implements Callbacks.OnTracksLoadedListener,
        Callbacks.RecyclerViewClickListener {

    @Bean
    TracklistAllFragmentView mTracklistAllFragmentView;

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    @Bean
    Utils utils;

    @Bean
    TrackProvider mTrackProvider;

    private Callbacks.OnSelectedTrackListener mOnSelectedTrackListener;
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener;

    @AfterViews
    void initViews() {
        mTrackProvider.loadAllTracks(this);
        mTracklistAllFragmentView.setOnRefreshListener(mOnRefreshListener);
    }

    @UiThread
    @Override
    public void onTrackLoaded(ArrayList<TrackModel> tracks) {
        mTracklistAllFragmentView.setTrackList(tracks);
        mTracklistAllFragmentView.setOnRecyclerClickListener(this);
        mTracklistAllFragmentView.hideProgressBar();
        mTracklistAllFragmentView.setRefreshing(false);
    }

    @Override
    public void onTrackLoaded(TrackModel trackModel) {

    }

    @Override
    public void onClick(View view, int position) {
        mOnSelectedTrackListener.onSelectedTrack(mTracklistAllFragmentView.getData(position));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnSelectedTrackListener = (Callbacks.OnSelectedTrackListener) activity;
        mOnRefreshListener = (SwipeRefreshLayout.OnRefreshListener) activity;
    }

    @Subscribe
    public void onUpdate(UpdateLibraryCompletedEvent event) {
        mTrackProvider.loadAllTracks(this);
    }

    @Subscribe
    public void onUpdateStarted(UpdateLibraryStartedEvent event) {
        mTracklistAllFragmentView.setRefreshing(true);
    }
}