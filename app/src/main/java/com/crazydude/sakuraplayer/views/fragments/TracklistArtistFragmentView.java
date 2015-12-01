package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.ArtistAdapter;
import com.crazydude.sakuraplayer.common.RecyclerViewTouchListener;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.ArtistModel;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;

/**
 * Created by Crazy on 27.05.2015.
 */
public class TracklistArtistFragmentView {

    @Bind(R.id.fragment_tracklist_artist_recycler)
    RecyclerView mRecyclerView;

    @Inject
    ArtistAdapter mArtistAdapter;

    @Bind(R.id.fragment_tracklist_artist_refresher)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    @Named("Activity")
    Context mContext;

    public void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mArtistAdapter);
    }

    public void setOnRecyclerClickListener(Callbacks.RecyclerViewClickListener listener) {
        mArtistAdapter.setOnRecyclerViewClickListener(listener);
    }

    public ArtistModel getData(int position) {
        return mArtistAdapter.getData(position);
    }

    public void setData(Cursor cursor) {
        mArtistAdapter.setCursor(cursor);
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeRefreshLayout.setOnRefreshListener(listener);
    }

    public void setRefreshing(boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
    }
}
