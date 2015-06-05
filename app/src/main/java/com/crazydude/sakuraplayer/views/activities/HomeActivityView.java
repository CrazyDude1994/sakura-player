package com.crazydude.sakuraplayer.views.activities;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.crazydude.sakuraplayer.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnAfterSplashScreenListener;

/**
 * Created by Crazy on 16.05.2015.
 */
@EBean
public class HomeActivityView extends BaseActivityView implements Animator.AnimatorListener {

    @RootContext
    AppCompatActivity mActivity;

    @ViewById(R.id.activity_home_splash_screen)
    View mSplashScreenImage;

    @ViewById(R.id.activity_home_toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.navigation_drawer)
    DrawerLayout mDrawerLayout;


    private OnAfterSplashScreenListener mOnAfterSplashScreen;
    private ActionBarDrawerToggle mDrawerToggle;

    @AfterViews
    void initViews() {
        initToolbar();
        initNavigationDrawer();
    }

    public void setOnAfterSplashScreenListener(OnAfterSplashScreenListener listener) {
        mOnAfterSplashScreen = listener;
    }

    @UiThread
    public void showToolbar() {
        mToolbar.setVisibility(View.VISIBLE);
    }

    @UiThread
    public void hideToolbar() {
        mToolbar.setVisibility(View.INVISIBLE);
    }

    private void initToolbar() {
        mActivity.setSupportActionBar(mToolbar);
        mActivity.getSupportActionBar().setHomeButtonEnabled(true);
//        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initNavigationDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(mActivity, mDrawerLayout, mToolbar,
                R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @UiThread
    public void hideSplashScreen(final int duration) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator anim = ObjectAnimator.ofFloat(mSplashScreenImage, "alpha", 1f, 0f);
                anim.setDuration(500);
                anim.addListener(HomeActivityView.this);
                anim.start();
            }
        }, duration);
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mSplashScreenImage.setVisibility(View.GONE);
        if (mOnAfterSplashScreen != null) {
            mOnAfterSplashScreen.onAfterSplashScreen();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
