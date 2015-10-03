package com.crazydude.sakuraplayer.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.views.fragments.TracklistFragmentView;

import javax.inject.Inject;

/**
 * Created by Crazy on 25.05.2015.
 */
public class TracklistFragment extends BaseFragment {

    @Inject
    TracklistFragmentView mTracklistFragmentView;

    @Inject
    LastfmApiManager mLastfmApiManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViews();
        return inflater.inflate(R.layout.fragment_tracklist, container, false);
    }

    void initViews() {
        mTracklistFragmentView.initViewPager(this);
    }
}
