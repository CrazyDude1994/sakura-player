package com.crazydude.sakuraplayer.views.fragments;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.RecommendsAdapter;
import com.crazydude.sakuraplayer.common.RecyclerViewTouchListener;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.net.ArtistResponse;
import com.crazydude.sakuraplayer.models.net.RecommendationsResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by kartavtsev.s on 05.06.2015.
 */
@EBean
public class RecommendsFragmentView extends BaseFragmentView {

    @RootContext
    AppCompatActivity mActivity;

    @ViewById(R.id.fragment_recommendations_recycler)
    RecyclerView mRecyclerView;

    private RecommendsAdapter mRecommendsAdapter;
    private GridLayoutManager mGridLayoutManager;

    @AfterViews
    void initViews() {
        mRecommendsAdapter = new RecommendsAdapter();
        mGridLayoutManager = new GridLayoutManager(mActivity, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mRecommendsAdapter);
    }

    public void setOnRecyclerClickListener(Callbacks.RecyclerViewClickListener listener) {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(mActivity, listener, mRecyclerView));
    }

    @UiThread
    public void setData(List<ArtistResponse> data) {
        mRecommendsAdapter.setData(data);
    }

    public ArtistResponse getData(int position) {
        return mRecommendsAdapter.getData(position);
    }
}
