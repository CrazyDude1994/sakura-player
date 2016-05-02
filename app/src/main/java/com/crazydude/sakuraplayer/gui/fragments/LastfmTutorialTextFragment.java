package com.crazydude.sakuraplayer.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialTextFragmentView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
public class LastfmTutorialTextFragment extends BaseFragment {

    @Inject
    LastfmTutorialTextFragmentView mLastfmTutorialTextFragmentView;

    private String tutorialText;

    public static LastfmTutorialTextFragment newInstance(String tutorialText) {
        LastfmTutorialTextFragment fragment = new LastfmTutorialTextFragment();
        fragment.tutorialText = tutorialText;
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_lastfm_tutorial_text;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        ButterKnife.bind(mLastfmTutorialTextFragmentView, rootView);
        mLastfmTutorialTextFragmentView.setTutorialText(tutorialText);
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}
