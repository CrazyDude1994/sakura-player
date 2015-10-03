package com.crazydude.sakuraplayer.views.fragments;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by kartavtsev.s on 26.06.2015.
 */
abstract public class BaseRefreshingView extends BaseFragmentView {

    protected SwipeRefreshLayout mSwipeRefreshLayout;

    public BaseRefreshingView() {
        super();
        initBaseViews();
    }

    private void initBaseViews() {
        injectSwipeRefreshLayout();
    }

    public void setRefreshing(boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeRefreshLayout.setOnRefreshListener(listener);
    }

    abstract public void injectSwipeRefreshLayout();
}