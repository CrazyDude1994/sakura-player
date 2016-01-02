package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistAdapter;
import com.crazydude.sakuraplayer.gui.decorators.DividerItemDecoration;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.venmo.cursor.IterableCursor;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.RecyclerViewClickListener;

/**
 * Created by Crazy on 16.05.2015.
 */
public class TracklistAllFragmentView {

    @Bind(R.id.fragment_tracklist_alltracks_recycler)
    RecyclerView mRecyclerView;

    @Bind(R.id.fragment_tracklist_alltracks_refresher)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    TracklistAdapter mTracklistAdapter;

    @Inject
    @Named("Activity")
    Context mContext;

    public void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mTracklistAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
    }

    public void setOnRecyclerClickListener(RecyclerViewClickListener listener) {
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
}
