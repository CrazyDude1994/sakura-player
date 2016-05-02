package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistPagerAdapter;
import com.crazydude.sakuraplayer.gui.fragments.TracklistFragment;

import butterknife.Bind;

/**
 * Created by Crazy on 16.05.2015.
 */
public class TracklistFragmentView {

    @Bind(R.id.fragment_tracklist_viewpager)
    ViewPager mViewPager;

    @Bind(R.id.fragment_tracklist_tabs)
    TabLayout mTabLayout;

    private TracklistPagerAdapter mPagerAdapter;

    public void initViewPager(TracklistFragment parentFragment) {
        if (mPagerAdapter == null) {
            mPagerAdapter = new TracklistPagerAdapter(parentFragment.getChildFragmentManager());
        }
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
