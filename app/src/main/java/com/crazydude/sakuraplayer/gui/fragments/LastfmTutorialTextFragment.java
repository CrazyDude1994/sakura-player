package com.crazydude.sakuraplayer.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialTextFragmentView;

import javax.inject.Inject;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
public class LastfmTutorialTextFragment extends BaseFragment {

    @Inject
    LastfmTutorialTextFragmentView mLastfmTutorialTextFragmentView;

    String tutorialText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViews();
        return inflater.inflate(R.layout.fragment_lastfm_tutorial_text, container, false);
    }

    void initViews() {
        mLastfmTutorialTextFragmentView.setTutorialText(tutorialText);
    }
}
