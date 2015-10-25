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

    public static LastfmTutorialTextFragment newInstance(String tutorialText) {
        LastfmTutorialTextFragment fragment = new LastfmTutorialTextFragment();
        fragment.tutorialText = tutorialText;
        return fragment;
    }

    String tutorialText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lastfm_tutorial_text, container, false);
        getActivityComponent().inject(this);
        ButterKnife.bind(mLastfmTutorialTextFragmentView, view);
        initViews();
        return view;
    }

    void initViews() {
        mLastfmTutorialTextFragmentView.setTutorialText(tutorialText);
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}
