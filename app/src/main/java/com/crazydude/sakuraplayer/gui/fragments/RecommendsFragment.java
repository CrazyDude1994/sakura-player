package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.models.net.ArtistResponse;
import com.crazydude.sakuraplayer.models.net.RecommendationsResponse;
import com.crazydude.sakuraplayer.views.fragments.RecommendsFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by CrazyDude on 17.03.2015.
 */
@EFragment(R.layout.fragment_recommendations)
public class RecommendsFragment extends BaseFragment implements Callbacks.OnResponseListener<RecommendationsResponse>, Callbacks.RecyclerViewClickListener {

    @Bean
    RecommendsFragmentView mRecommendsFragmentView;

    @Bean
    LastfmApiManager mLastfmApiManager;

    private Callbacks.OnSelectedArtistListener mOnSelectedArtistListener;

    @AfterViews
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
    public void onNetworkError(String message) {
        mRecommendsFragmentView.hideProgressBar();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnSelectedArtistListener = (Callbacks.OnSelectedArtistListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + Callbacks.OnSelectedArtistListener.class.getSimpleName());
        }
    }

    @Override
    public void onClick(View view, int position) {
        ArtistResponse data = mRecommendsFragmentView.getData(position);
        mOnSelectedArtistListener.onSelectedArtist(data.getName(), data.getMbid());
    }
}