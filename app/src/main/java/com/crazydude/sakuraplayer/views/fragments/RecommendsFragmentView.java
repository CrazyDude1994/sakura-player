package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.RecommendsAdapter;
import com.crazydude.sakuraplayer.common.RecyclerViewTouchListener;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.net.ArtistResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;

/**
 * Created by kartavtsev.s on 05.06.2015.
 */
public class RecommendsFragmentView {

    @Bind(R.id.fragment_recommendations_recycler)
    RecyclerView mRecyclerView;

    @Inject
    @Named("Activity")
    Context mContext;

    private RecommendsAdapter mRecommendsAdapter;
    private GridLayoutManager mGridLayoutManager;

    public RecommendsFragmentView() {
        initViews();
    }

    public void initViews() {
        mRecommendsAdapter = new RecommendsAdapter();
        mGridLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mRecommendsAdapter);
    }

    public void setOrientation(int orientation) {
        mGridLayoutManager.setOrientation(orientation);
    }

    public void setColumnCount(int columnCount) {
        mGridLayoutManager.setSpanCount(columnCount);
    }

    public void setOnRecyclerClickListener(Callbacks.RecyclerViewClickListener listener) {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(mContext, listener,
                mRecyclerView));
    }

    public void setData(List<ArtistResponse> data) {
        mRecommendsAdapter.setData(data);
    }

    public ArtistResponse getData(int position) {
        return mRecommendsAdapter.getData(position);
    }
}
