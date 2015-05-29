package com.crazydude.sakuraplayer.views.fragments;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.andraskindler.parallaxviewpager.ParallaxViewPager;
import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.LastfmTutorialPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
@EBean
public class LastfmTutorialFragmentView extends BaseFragmentView {

    @ViewById(R.id.fragment_lastfm_tutorial_viewpager)
    ParallaxViewPager mViewPager;

    @ViewById(R.id.fragment_lastfm_tutorial_indicator)
    CirclePageIndicator mPageIndicator;

    @ViewById(R.id.fragment_lastfm_tutorial_button)
    Button mTutorialButton;

    @RootContext
    FragmentActivity mContext;

    private LastfmTutorialPagerAdapter mPagerAdapter;

    @AfterViews
    void initViews() {
        mPagerAdapter = new LastfmTutorialPagerAdapter(mContext.getSupportFragmentManager());

        mViewPager.setAdapter(mPagerAdapter);
        mPageIndicator.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(mPageIndicator);
    }

    public void showLoginButton() {
        mTutorialButton.setVisibility(View.VISIBLE);
    }

    public void hideLoginButton() {
        mTutorialButton.setVisibility(View.GONE);
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewPager.addOnPageChangeListener(listener);
    }

    public int getPagesCount() {
        if (mPagerAdapter != null) {
            return mPagerAdapter.getCount();
        }
        return 0;
    }
}
