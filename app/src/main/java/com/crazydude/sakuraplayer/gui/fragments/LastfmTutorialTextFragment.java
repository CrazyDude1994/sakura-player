package com.crazydude.sakuraplayer.gui.fragments;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialTextFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
@EFragment(R.layout.fragment_lastfm_tutorial_text)
public class LastfmTutorialTextFragment extends BaseFragment {

    @Bean
    LastfmTutorialTextFragmentView mLastfmTutorialTextFragmentView;

    @FragmentArg
    String tutorialText;

    @AfterViews
    void initViews() {
        mLastfmTutorialTextFragmentView.setTutorialText(tutorialText);
    }
}
