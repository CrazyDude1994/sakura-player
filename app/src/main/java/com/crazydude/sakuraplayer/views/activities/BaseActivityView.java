package com.crazydude.sakuraplayer.views.activities;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.crazydude.sakuraplayer.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Crazy on 16.05.2015.
 */
public class BaseActivityView {

    @Inject
    Context mContext;

    @Inject
    AppCompatActivity mAppCompatActivity;

    @Bind(R.id.activity_home_progressbar)
    View mProgressBar;

    @Bind(R.id.activity_home_placeholder_progressbar)
    View mContentProgressBar;

    public BaseActivityView() {
        ButterKnife.bind(this, mAppCompatActivity);
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    public void showContentProgressBar() {
        mContentProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideContentProgressBar() {
        mContentProgressBar.setVisibility(View.GONE);
    }

    public void showInfoDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(mContext.getString(R.string.ok), null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
