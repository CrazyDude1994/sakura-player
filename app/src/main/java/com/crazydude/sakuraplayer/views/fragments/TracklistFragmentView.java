package com.crazydude.sakuraplayer.views.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistPagerAdapter;
import com.crazydude.sakuraplayer.gui.fragments.TracklistAllFragment_;
import com.crazydude.sakuraplayer.views.fragments.BaseFragmentView;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

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
    IconPageIndicator mIconPageIndicator;

    @RootContext
    FragmentActivity mContext;

    private PagerAdapter mPagerAdapter;

    @AfterViews
    void initViews() {
        mPagerAdapter = new TracklistPagerAdapter(mContext.getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        mIconPageIndicator.setViewPager(mViewPager);
    }
}
