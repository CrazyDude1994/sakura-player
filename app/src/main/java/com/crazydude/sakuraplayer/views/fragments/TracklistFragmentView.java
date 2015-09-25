package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistPagerAdapter;
import com.crazydude.sakuraplayer.gui.fragments.BaseFragment;

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

    @ViewById(R.id.fragment_tracklist_tabs)
    TabLayout mTabLayout;

    @RootContext
    Context mContext;
    private TracklistPagerAdapter mPagerAdapter;

    @AfterViews
    void initViews() {
        showToolbarShadow();
    }

    public void initViewPager(BaseFragment parentFragment) {
        if (mPagerAdapter == null) {
            mPagerAdapter = new TracklistPagerAdapter(parentFragment.getChildFragmentManager());
        }
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
