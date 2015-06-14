package com.crazydude.sakuraplayer.views.fragments;

import com.crazydude.sakuraplayer.gui.activity.HomeActivity;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

/**
 * Created by Crazy on 16.05.2015.
 */
@EBean
public class BaseFragmentView {

    @RootContext
    HomeActivity mBaseActivity;

    @UiThread
    public void showProgressBar() {
        mBaseActivity.getHomeActivityView().showContentProgressBar();
    }

    @UiThread
    public void hideProgressBar() {
        mBaseActivity.getHomeActivityView().hideContentProgressBar();
    }

    @UiThread
    public void hideToolbarShadow() {
        mBaseActivity.getHomeActivityView().hideToolbarShadow();
    }

    @UiThread
    void showToolbarShadow() {
        mBaseActivity.getHomeActivityView().showToolbarShadow();
    }
}
