package com.crazydude.sakuraplayer.gui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.gui.fragments.PlayerFragment_;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.managers.PlayerBinder;
import com.crazydude.sakuraplayer.services.PlayerService_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_home)
public class HomeActivity extends FragmentActivity {

    @ViewById(R.id.activity_home_progressbar)
    View mProgressBar;

    @ViewById(R.id.activity_home_splash_screen)
    View mSplashScreenImage;

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    private PlayerServiceConnection mPlayerServiceConnection = new PlayerServiceConnection();

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

    private class PlayerServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ((PlayerBinder) service).play("/sdcard/Download/1.mp3");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private void afterSplash() {
        PlayerService_.intent(this).start();
//        Intent intent = new Intent(this, PlayerService_.class); // note the underscore
//        bindService(intent, mPlayerServiceConnection, Context.BIND_AUTO_CREATE);
        //ArrayList<String> tracks = mMusicLibraryManager.getAllTracks();
        //HashSet<String> tracks = mMusicLibraryManager.getArtistList();
//        getFragmentManager().beginTransaction()
//                .replace(R.id.activity_home_placeholder, PlayerFragment_.builder().build())
//                .commit();
        //startActivity(new Intent(this, TracklistActivity_.class));
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_home_placeholder, new PlayerFragment_().builder().build())
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
