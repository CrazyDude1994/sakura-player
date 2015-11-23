package com.crazydude.sakuraplayer.views.fragments;

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

    @Bind(R.id.fragment_tracklist_refresher)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private TracklistPagerAdapter mPagerAdapter;

    public void initViewPager(TracklistFragment parentFragment) {
        if (mPagerAdapter == null) {
            mPagerAdapter = new TracklistPagerAdapter(parentFragment.getChildFragmentManager());
        }
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mSwipeRefreshLayout.setEnabled(state == ViewPager.SCROLL_STATE_IDLE);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(parentFragment);
    }

    public void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.setRefreshing(refreshing);
    }
}
