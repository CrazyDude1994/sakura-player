package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.events.UpdateLibraryCompletedEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryStartedEvent;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.views.fragments.TracklistAllFragmentView;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by CrazyDude on 13.04.2015.
 */
public class TracklistAllFragment extends BaseFragment implements Callbacks.OnTracksLoadedListener,
        Callbacks.RecyclerViewClickListener {

    @Inject
    TracklistAllFragmentView mTracklistAllFragmentView;

    @Inject
    MusicLibraryManager mMusicLibraryManager;

    @Inject
    Utils utils;

    @Inject
    TrackProvider mTrackProvider;

    private Callbacks.OnSelectedTrackListener mOnSelectedTrackListener;
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViews();
        return inflater.inflate(R.layout.fragment_tracklist_alltracks, container, false);
    }

    void initViews() {
        mTrackProvider.loadAllTracks(this);
    }

    @Override
    public void onTrackLoaded(ArrayList<TrackModel> tracks) {
        mTracklistAllFragmentView.setTrackList(tracks);
        mTracklistAllFragmentView.setOnRecyclerClickListener(this);
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
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}