package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.models.net.ArtistResponse;
import com.crazydude.sakuraplayer.models.net.RecommendationsResponse;
import com.crazydude.sakuraplayer.views.fragments.RecommendsFragmentView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by CrazyDude on 17.03.2015.
 */
public class RecommendsFragment extends BaseFragment implements Callbacks.RecyclerViewClickListener {

    @Inject
    RecommendsFragmentView mRecommendsFragmentView;

    @Inject
    LastfmApiManager mLastfmApiManager;

    private Callbacks.OnSelectedLastfmArtistListener mOnSelectedLastfmArtistListener;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_recommendations;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        getActivityComponent().inject(mRecommendsFragmentView);
        ButterKnife.bind(mRecommendsFragmentView, rootView);
        mRecommendsFragmentView.initViews();
        mRecommendsFragmentView.setOnRecyclerClickListener(this);
        mLastfmApiManager.getRecommendedArtists(0, 100).subscribe(new Action1<RecommendationsResponse>() {
            @Override
            public void call(RecommendationsResponse recommendationsResponse) {
                mRecommendsFragmentView.setData(recommendationsResponse.getRecommendations().getArtists());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
            }
        });
    }

/*    @Override
    public void onSuccess(RecommendationsResponse response) {
        mRecommendsFragmentView.setData(response.getRecommendations().getArtists());
    }*/

    @Override
    public void onPause() {
        super.onPause();
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

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}