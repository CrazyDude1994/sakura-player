package com.crazydude.sakuraplayer.views.fragments;

import android.support.v4.widget.SwipeRefreshLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

/**
 * Created by kartavtsev.s on 26.06.2015.
 */
@EBean
abstract public class BaseRefreshingView extends BaseFragmentView {

    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @AfterViews
    void initBaseViews() {
        injectSwipeRefreshLayout();
    }

    @UiThread
    public void setRefreshing(boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeRefreshLayout.setOnRefreshListener(listener);
    }

    abstract public void injectSwipeRefreshLayout();
}