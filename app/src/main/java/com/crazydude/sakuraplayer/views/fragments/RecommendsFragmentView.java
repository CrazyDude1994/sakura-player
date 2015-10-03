package com.crazydude.sakuraplayer.views.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.RecommendsAdapter;
import com.crazydude.sakuraplayer.common.RecyclerViewTouchListener;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.net.ArtistResponse;

import java.util.List;

import butterknife.Bind;

/**
 * Created by kartavtsev.s on 05.06.2015.
 */
public class RecommendsFragmentView extends BaseFragmentView {

    @Bind(R.id.fragment_recommendations_recycler)
    RecyclerView mRecyclerView;

    private RecommendsAdapter mRecommendsAdapter;
    private GridLayoutManager mGridLayoutManager;

    public RecommendsFragmentView() {
        super();
        initViews();
    }

    void initViews() {
        mRecommendsAdapter = new RecommendsAdapter();
        mGridLayoutManager = new GridLayoutManager(mActivity, 2);
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
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(mActivity, listener,
                mRecyclerView));
    }

    public void setData(List<ArtistResponse> data) {
        mRecommendsAdapter.setData(data);
    }

    public ArtistResponse getData(int position) {
        return mRecommendsAdapter.getData(position);
    }
}
