package com.crazydude.sakuraplayer.views.activities;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.crazydude.sakuraplayer.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Crazy on 16.05.2015.
 */
@EBean
public class BaseActivityView {

    @RootContext
    Context mContext;

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

    @UiThread
    public void showInfoDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(mContext.getString(R.string.ok), null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
