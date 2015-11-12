package com.crazydude.sakuraplayer.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.views.fragments.TracklistFragmentView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Crazy on 25.05.2015.
 */
public class TracklistFragment extends BaseFragment {

    @Inject
    TracklistFragmentView mTracklistFragmentView;

    @Inject
    LastfmApiManager mLastfmApiManager;

    public static TracklistFragment newInstance() {
        return new TracklistFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tracklist;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        ButterKnife.bind(mTracklistFragmentView, rootView);
        mTracklistFragmentView.initViewPager(this);
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}
