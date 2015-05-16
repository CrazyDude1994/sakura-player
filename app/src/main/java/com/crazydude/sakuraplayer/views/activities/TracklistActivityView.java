package com.crazydude.sakuraplayer.views.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.fragments.TracklistAllFragment_;
import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.IconPagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Crazy on 16.05.2015.
 */
@EBean
public class TracklistActivityView extends BaseActivityView {

    static final int PAGE_COUNT = 4;
    static final String[] CONTENT = new String[]{"This", "Is", "A", "Test"};
    static final int[] ICONS = new int[]{R.drawable.ic_action_favorite, R.drawable.ic_launcher,
            R.drawable.ic_action_favorite, R.drawable.ic_launcher};

    @ViewById(R.id.activity_tracklist_viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.activity_tracklist_viewpager_titles)
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

    private class TracklistPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

        public TracklistPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getIconResId(int index) {
            return ICONS[index];
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
