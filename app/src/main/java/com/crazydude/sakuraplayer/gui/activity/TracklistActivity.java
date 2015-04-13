package com.crazydude.sakuraplayer.gui.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.fragments.TracklistAllFragment_;
import com.viewpagerindicator.TitlePageIndicator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_tracklist)
public class TracklistActivity extends FragmentActivity {


    static final int PAGE_COUNT = 4;
    static final String[] CONTENT = new String[] { "This", "Is", "A", "Test"};

    @ViewById(R.id.activity_tracklist_viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.activity_tracklist_viewpager_titles)
    TitlePageIndicator mTitleIndicator;

    PagerAdapter mPagerAdapter;

    @AfterViews
    void initViews() {
        mPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        mTitleIndicator.setViewPager(mViewPager);
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {



        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TracklistAllFragment_.builder().build();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length];
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }

}
