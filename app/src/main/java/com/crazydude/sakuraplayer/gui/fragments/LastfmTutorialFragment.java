package com.crazydude.sakuraplayer.gui.fragments;

import android.support.v4.view.ViewPager;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
@EFragment(R.layout.fragment_lastfm_tutorial)
public class LastfmTutorialFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @Bean
    LastfmTutorialFragmentView mLastfmTutorialFragmentView;

    @AfterViews
    void initViews() {
        mLastfmTutorialFragmentView.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if ((position - 1) == mLastfmTutorialFragmentView.getPagesCount()) {
            mLastfmTutorialFragmentView.showLoginButton();
        } else {
            mLastfmTutorialFragmentView.hideLoginButton();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
