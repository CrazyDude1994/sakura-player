package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.models.net.ArtistResponse;
import com.crazydude.sakuraplayer.models.net.RecommendationsResponse;
import com.crazydude.sakuraplayer.views.fragments.RecommendsFragmentView;

import javax.inject.Inject;

/**
 * Created by CrazyDude on 17.03.2015.
 */
public class RecommendsFragment extends BaseFragment implements Callbacks.OnResponseListener<RecommendationsResponse>, Callbacks.RecyclerViewClickListener {

    @Inject
    RecommendsFragmentView mRecommendsFragmentView;

    @Inject
    LastfmApiManager mLastfmApiManager;

    private Callbacks.OnSelectedLastfmArtistListener mOnSelectedLastfmArtistListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViews();
        return inflater.inflate(R.layout.fragment_recommendations, container, false);
    }

    void initViews() {
        mRecommendsFragmentView.showProgressBar();
        mRecommendsFragmentView.setOnRecyclerClickListener(this);
        mLastfmApiManager.getRecommendedArtists(0, 100, this);
    }

    @Override
    public void onSuccess(RecommendationsResponse response) {
        mRecommendsFragmentView.hideProgressBar();
        mRecommendsFragmentView.setData(response.getRecommendations().getArtists());
    }

    @Override
    public void onLastfmError(String message, Integer code) {
        mRecommendsFragmentView.hideProgressBar();
    }

    @Override
    public void onPause() {
        super.onPause();
        mRecommendsFragmentView.hideProgressBar();
    }

    @Override
    public void onNetworkError(String message) {
        mRecommendsFragmentView.hideProgressBar();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnSelectedLastfmArtistListener = (Callbacks.OnSelectedLastfmArtistListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + Callbacks.OnSelectedLastfmArtistListener.class.getSimpleName());
        }
    }

    @Override
    public void onClick(View view, int position) {
        ArtistResponse data = mRecommendsFragmentView.getData(position);
        mOnSelectedLastfmArtistListener.onSelecteLastfmArtist(data.getName(), data.getMbid());
    }
}