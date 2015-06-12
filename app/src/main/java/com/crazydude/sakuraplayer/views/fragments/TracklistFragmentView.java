package com.crazydude.sakuraplayer.views.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistPagerAdapter;
import com.crazydude.sakuraplayer.gui.fragments.BaseFragment;
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
    Context mContext;
    private TracklistPagerAdapter mPagerAdapter;

    @AfterViews
    void initViews() {

    }

    public void initViewPager(BaseFragment parentFragment) {
        if (mPagerAdapter == null) {
            mPagerAdapter = new TracklistPagerAdapter(parentFragment.getChildFragmentManager());
        }
        mViewPager.setAdapter(mPagerAdapter);

        mTablistIndicator.setViewPager(mViewPager);
    }
}
