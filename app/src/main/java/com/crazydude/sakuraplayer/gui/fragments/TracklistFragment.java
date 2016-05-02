package com.crazydude.sakuraplayer.gui.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistPagerAdapter;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by Crazy on 25.05.2015.
 */
public class TracklistFragment extends BaseFragment {

    @Bind(R.id.fragment_tracklist_viewpager)
    ViewPager mViewPager;

    @Bind(R.id.fragment_tracklist_tabs)
    TabLayout mTabLayout;
    @Inject
    LastfmApiManager mLastfmApiManager;
    private TracklistPagerAdapter mPagerAdapter;

    public static TracklistFragment newInstance() {
        return new TracklistFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tracklist;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        initViewPager();
    }

    public void initViewPager() {
        if (mPagerAdapter == null) {
            mPagerAdapter = new TracklistPagerAdapter(getChildFragmentManager());
        }
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}
