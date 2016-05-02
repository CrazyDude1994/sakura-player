package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.NewReleasesAdapter;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
public class LastReleasesFragmentView {

    @Bind(R.id.fragment_last_releases_recycler)
    RecyclerView mRecyclerView;

    @Inject
    Context mContext;

    private LinearLayoutManager mLinearLayout;
    private NewReleasesAdapter mAdapter;

    public LastReleasesFragmentView() {
        super();
        initViews();
    }

    void initViews() {
        mAdapter = new NewReleasesAdapter();
        mLinearLayout = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayout);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setData(AlbumResponse data) {
        mAdapter.setData(data.getAlbums().getAlbum());
    }

}
