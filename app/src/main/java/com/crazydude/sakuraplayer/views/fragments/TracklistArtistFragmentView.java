package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.ArtistAdapter;
import com.crazydude.sakuraplayer.common.RecyclerViewTouchListener;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.ArtistModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by Crazy on 27.05.2015.
 */
public class TracklistArtistFragmentView {

    @Bind(R.id.fragment_tracklist_artist_recycler)
    RecyclerView mRecyclerView;

    @Bind(R.id.fragment_tracklist_artist_refresher)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    ArtistAdapter mArtistAdapter;

    @Inject
    Context mContext;

    void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mArtistAdapter);
    }

    public void setOnRecyclerClickListener(Callbacks.RecyclerViewClickListener listener) {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(mContext, listener,
                mRecyclerView));
    }

    public void setArtistList(ArrayList<ArtistModel> models) {
        mArtistAdapter.setData(models);
    }
}
