package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.ArtistAdapter;
import com.crazydude.sakuraplayer.events.RequestUpdateLibraryEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryCompletedEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryStartedEvent;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.gui.decorators.DividerItemDecoration;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.squareup.otto.Subscribe;
import com.venmo.cursor.IterableCursor;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;

/**
 * Created by Crazy on 27.05.2015.
 */
public class TracklistArtistFragment extends BaseFragment implements Callbacks.RecyclerViewClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.fragment_tracklist_artist_recycler)
    RecyclerView mRecyclerView;

    @Inject
    ArtistAdapter mArtistAdapter;

    @Bind(R.id.fragment_tracklist_artist_refresher)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    @Named("Activity")
    Context mContext;

    @Inject
    MusicLibraryManager mMusicLibraryManager;

    private Callbacks.OnSelectedArtistListener mOnSelectedArtistListener;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tracklist_artist;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mArtistAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        setOnRecyclerClickListener(this);
        setOnRefreshListener(this);
        loadData();
    }

    private void loadData() {
        setData(mMusicLibraryManager.queryAllArtists());
    }

    @Override
    public void onClick(View view, int position) {
        mOnSelectedArtistListener.onSelectedArtist(getData(position));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnSelectedArtistListener = (Callbacks.OnSelectedArtistListener) activity;
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
    public void onLibraryUpdated(UpdateLibraryCompletedEvent event) {
        setRefreshing(false);
        loadData();
    }

    @Subscribe
    public void onLibraryUpdating(UpdateLibraryStartedEvent event) {
        setRefreshing(true);
    }

    public void setOnRecyclerClickListener(Callbacks.RecyclerViewClickListener listener) {
        mArtistAdapter.setOnRecyclerViewClickListener(listener);
    }

    public ArtistModel getData(int position) {
        return mArtistAdapter.getData(position);
    }

    public void setData(IterableCursor<ArtistModel> data) {
        mArtistAdapter.setCursor(data);
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeRefreshLayout.setOnRefreshListener(listener);
    }

    public void setRefreshing(boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
    }
}
