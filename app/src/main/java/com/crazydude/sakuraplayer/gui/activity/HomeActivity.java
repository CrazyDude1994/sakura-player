package com.crazydude.sakuraplayer.gui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Handler;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.gui.fragments.PlayerFragment_;
import com.crazydude.sakuraplayer.services.PlayerService;
import com.crazydude.sakuraplayer.services.PlayerService_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_home)
public class HomeActivity extends Activity {

    @ViewById(R.id.activity_home_progressbar)
    View mProgressBar;

    @ViewById(R.id.activity_home_splash_screen)
    View mSplashScreenImage;

    @Override
    protected void onStart() {
        super.onStart();
        //EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        //EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @AfterViews
    void initViews() {
        hideSplashScreen(Constants.SPLASH_DURATION);
    }

    private void afterSplash() {
        PlayerService_.intent(getApplicationContext()).action(PlayerService.ACTION_PLAY).start();
        getFragmentManager().beginTransaction()
                .replace(R.id.activity_home_placeholder, PlayerFragment_.builder().build())
                .commit();
    }


    public void hideSplashScreen(final int duration) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator anim = ObjectAnimator.ofFloat(mSplashScreenImage, "alpha", 1f, 0f);
                anim.setDuration(500);
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mSplashScreenImage.setVisibility(View.GONE);
                        afterSplash();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                anim.start();
            }
        }, duration);
    }

    @UiThread
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @UiThread
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
