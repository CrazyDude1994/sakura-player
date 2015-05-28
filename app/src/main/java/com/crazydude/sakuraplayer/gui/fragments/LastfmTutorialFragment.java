package com.crazydude.sakuraplayer.gui.fragments;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
@EFragment(R.layout.fragment_lastfm_tutorial)
public class LastfmTutorialFragment extends BaseFragment {

    @Bean
    LastfmTutorialFragmentView mLastfmTutorialFragmentView;

    @AfterViews
    void initViews() {

    }
}
