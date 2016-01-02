package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.events.RequestUpdateLibraryEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryCompletedEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryStartedEvent;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.views.fragments.TracklistAllFragmentView;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by CrazyDude on 13.04.2015.
 */
public class TracklistAllFragment extends BaseFragment implements Callbacks.RecyclerViewClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    TracklistAllFragmentView mTracklistAllFragmentView;

    @Inject
    MusicLibraryManager mMusicLibraryManager;

    @Inject
    Utils utils;

    private Callbacks.OnSelectedTrackListener mOnSelectedTrackListener;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tracklist_alltracks;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        getActivityComponent().inject(mTracklistAllFragmentView);
        ButterKnife.bind(mTracklistAllFragmentView, rootView);
        mTracklistAllFragmentView.initViews();
        mTracklistAllFragmentView.setOnRecyclerClickListener(this);
        mTracklistAllFragmentView.setOnRefreshListener(this);
        loadData();
    }

    private void loadData() {
        mTracklistAllFragmentView.setData(mMusicLibraryManager.queryAllTracks());
    }

    @Override
    public void onClick(View view, int position) {
        mOnSelectedTrackListener.onSelectedTrack(mTracklistAllFragmentView.getData(position));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnSelectedTrackListener = (Callbacks.OnSelectedTrackListener) activity;
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }

    @Override
    public void onRefresh() {
        mBus.post(new RequestUpdateLibraryEvent());
    }

    @Subscribe
    public void onLibraryUpdating(UpdateLibraryStartedEvent event) {
        mTracklistAllFragmentView.setRefreshing(true);
    }

    @Subscribe
    public void onLibraryUpdated(UpdateLibraryCompletedEvent event) {
        mTracklistAllFragmentView.setRefreshing(false);
        loadData();
    }
}