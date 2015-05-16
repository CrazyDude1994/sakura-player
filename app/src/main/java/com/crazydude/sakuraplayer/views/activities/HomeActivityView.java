package com.crazydude.sakuraplayer.views.activities;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;

import com.crazydude.sakuraplayer.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnAfterSplashScreenListener;

/**
 * Created by Crazy on 16.05.2015.
 */
@EBean
public class HomeActivityView extends BaseActivityView implements Animator.AnimatorListener {

    @ViewById(R.id.activity_home_splash_screen)
    View mSplashScreenImage;

    private OnAfterSplashScreenListener mOnAfterSplashScreen;

    public void setOnAfterSplashScreenListener(OnAfterSplashScreenListener listener) {
        mOnAfterSplashScreen = listener;
    }

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
