package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;

import com.crazydude.sakuraplayer.gui.activity.HomeActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Crazy on 16.05.2015.
 */
public class BaseFragmentView {

    @Inject
    HomeActivity mActivity;

    @Inject
    Context mContext;

    public BaseFragmentView() {
        ButterKnife.bind(this, mActivity);
    }

    public void showProgressBar() {
        mActivity.getHomeActivityView().showContentProgressBar();
    }

    public void hideProgressBar() {
        mActivity.getHomeActivityView().hideContentProgressBar();
    }

    public void hideToolbarShadow() {
        mActivity.getHomeActivityView().hideToolbarShadow();
    }

    void showToolbarShadow() {
        mActivity.getHomeActivityView().showToolbarShadow();
    }
}
