package com.crazydude.sakuraplayer.views.activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.crazydude.sakuraplayer.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Crazy on 16.05.2015.
 */
public class BaseActivityView {

    protected AppCompatActivity mAppCompatActivity;

    @Bind(R.id.activity_home_progressbar)
    View mProgressBar;

    @Bind(R.id.activity_home_placeholder_progressbar)
    View mContentProgressBar;

    public BaseActivityView(AppCompatActivity activity) {
        mAppCompatActivity = activity;
        ButterKnife.bind(this, activity);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(mAppCompatActivity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(mAppCompatActivity.getString(R.string.ok), null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
