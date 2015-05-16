package com.crazydude.sakuraplayer.views.activities;

import android.view.View;

import com.crazydude.sakuraplayer.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Crazy on 16.05.2015.
 */
@EBean
public class BaseActivityView {

    @ViewById(R.id.activity_home_progressbar)
    View mProgressBar;

    @UiThread
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @UiThread
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
