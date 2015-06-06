package com.crazydude.sakuraplayer.gui.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.RecommendsAdapter;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.models.net.RecommendationsResponse;
import com.crazydude.sakuraplayer.views.fragments.RecommendsFragmentView;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by CrazyDude on 17.03.2015.
 */
@EFragment(R.layout.fragment_recommendations)
public class RecommendsFragment extends BaseFragment implements Callbacks.OnResponseListener<RecommendationsResponse> {

    @Bean
    RecommendsFragmentView mRecommendsFragmentView;

    @ViewById(R.id.fragment_recommendations_recycler)
    RecyclerView mRecyclerView;

    @Bean
    LastfmApiManager mLastfmApiManager;

    private RecommendsAdapter mRecommendsAdapter;
    private GridLayoutManager mGridLayoutManager;

    @AfterViews
    void initViews() {
        mRecommendsAdapter = new RecommendsAdapter();
        mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mRecommendsAdapter);
        mRecommendsFragmentView.showProgressBar();
        mLastfmApiManager.getRecommendedArtists(0, 100, this);
    }

    @UiThread
    @Override
    public void onSuccess(RecommendationsResponse response) {
        mRecommendsFragmentView.hideProgressBar();
        mRecommendsAdapter.setData(response.getRecommendations().getArtists());
    }

    @Override
    public void onLastfmError(String message, Integer code) {
        mRecommendsFragmentView.hideProgressBar();
    }

    @Override
    public void onNetworkError(String message) {
        mRecommendsFragmentView.hideProgressBar();
    }
}
