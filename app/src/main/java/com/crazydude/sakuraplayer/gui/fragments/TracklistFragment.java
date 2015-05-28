package com.crazydude.sakuraplayer.gui.fragments;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.views.fragments.TracklistFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Crazy on 25.05.2015.
 */
@EFragment(R.layout.fragment_tracklist)
public class TracklistFragment extends BaseFragment {

    @Bean
    TracklistFragmentView mTracklistFragmentView;

    @AfterViews
    void initViews() {

    }
}
