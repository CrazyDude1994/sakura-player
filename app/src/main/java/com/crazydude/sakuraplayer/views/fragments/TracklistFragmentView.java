package com.crazydude.sakuraplayer.views.fragments;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Crazy on 16.05.2015.
 */
@EBean
public class TracklistFragmentView extends BaseFragmentView {

    @ViewById(R.id.fragment_tracklist_viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.fragment_tracklist_viewpager_titles)
    TabPageIndicator mTablistIndicator;

    @RootContext
    FragmentActivity mContext;

    private PagerAdapter mPagerAdapter;

    @AfterViews
    void initViews() {
        mPagerAdapter = new TracklistPagerAdapter(mContext.getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        mTablistIndicator.setViewPager(mViewPager);
    }
}
