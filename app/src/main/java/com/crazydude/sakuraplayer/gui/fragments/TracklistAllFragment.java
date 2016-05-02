package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistAdapter;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.events.RequestUpdateLibraryEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryCompletedEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryStartedEvent;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.gui.decorators.DividerItemDecoration;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.squareup.otto.Subscribe;
import com.venmo.cursor.IterableCursor;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;

/**
 * Created by CrazyDude on 13.04.2015.
 */
public class TracklistAllFragment extends BaseFragment implements Callbacks.RecyclerViewClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    MusicLibraryManager mMusicLibraryManager;

    @Inject
    Utils utils;
    @Bind(R.id.fragment_tracklist_alltracks_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.fragment_tracklist_alltracks_refresher)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    TracklistAdapter mTracklistAdapter;
    @Inject
    @Named("Activity")
    Context mContext;
    private Callbacks.OnSelectedTrackListener mOnSelectedTrackListener;

    public void setOnRecyclerClickListener(Callbacks.RecyclerViewClickListener listener) {
        mTracklistAdapter.setOnRecyclerViewClickListener(listener);
    }

    public TrackModel getData(int position) {
        return mTracklistAdapter.getData(position);
    }

    public void setData(IterableCursor<TrackModel> cursor) {
        mTracklistAdapter.setCursor(cursor);
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeRefreshLayout.setOnRefreshListener(listener);
    }

    public void setRefreshing(boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tracklist_alltracks;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mTracklistAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        setOnRecyclerClickListener(this);
        setOnRefreshListener(this);
        loadData();
    }

    private void loadData() {
        setData(mMusicLibraryManager.queryAllTracks());
    }

    @Override
    public void onClick(View view, int position) {
        mOnSelectedTrackListener.onSelectedTrack(getData(position));
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
        setRefreshing(true);
    }

    @Subscribe
    public void onLibraryUpdated(UpdateLibraryCompletedEvent event) {
        setRefreshing(false);
        loadData();
    }
}