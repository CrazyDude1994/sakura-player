package com.crazydude.sakuraplayer.gui.activity;

import android.content.Intent;
import android.net.Uri;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.gui.fragments.LastfmLoginFragment;
import com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialFragment_;
import com.crazydude.sakuraplayer.gui.fragments.TracklistFragment_;
import com.crazydude.sakuraplayer.interfaces.Preferences_;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.net.SessionResponse;
import com.crazydude.sakuraplayer.services.PlayerService_;
import com.crazydude.sakuraplayer.views.activities.HomeActivityView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnAfterSplashScreenListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnLastfmLoginListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnLastfmResponseListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnLastfmTutorialCompletedListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnNetworkErrorListener;

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements OnAfterSplashScreenListener,
        OnLastfmTutorialCompletedListener, OnLastfmLoginListener, OnNetworkErrorListener,
        OnLastfmResponseListener<SessionResponse> {

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    @Bean
    HomeActivityView mHomeActivityView;

    @Pref
    Preferences_ mPrefs;

    @Bean
    Utils mUtils;

    @Bean
    LastfmApiManager mLastfmApiManager;

    @AfterInject
    void init() {
        mLastfmApiManager.setOnLastfmResponseListener(this);
        mLastfmApiManager.setOnNetworkErrorListener(this);
    }

    @AfterViews
    void initViews() {
        mHomeActivityView.setOnAfterSplashScreenListener(this);
        mHomeActivityView.hideSplashScreen(Constants.SPLASH_DURATION);
    }

    @Override
    public void onAfterSplashScreen() {
        PlayerService_.intent(this).start();
        if (!mPrefs.isTutorialCompleted().get()) {
            switchFragment(LastfmTutorialFragment_.builder().build(), false, R.id.activity_home_placeholder);
        } else {
            switchToPlayerMode();
        }
    }

    private void switchToPlayerMode() {
        switchFragment(TracklistFragment_.builder().build(), false,
                R.id.activity_home_placeholder);
    }

    @Override
    public void onTutorialCompleted(boolean isLoginToLastfm) {
        mPrefs.isTutorialCompleted().put(true);
        if (isLoginToLastfm) {
            new LastfmLoginFragment().show(getFragmentManager(), "");
        } else {
            switchToPlayerMode();
        }
    }

    @Override
    public void onLastfmLogin(String login, String password) {
        mHomeActivityView.showProgressBar();
        mLastfmApiManager.login(login, password);
    }

    @Override
    public void onSuccess(SessionResponse response) {
        mHomeActivityView.hideProgressBar();
        mPrefs.lastfmToken().put(response.getSession().getKey());
        switchToPlayerMode();
    }

    @Override
    public void onLastfmRegister() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.lastfm.com/join"));
        startActivityForResult(intent, 0);
    }

    @Override
    public void onNetworkError(String message) {
        mHomeActivityView.hideProgressBar();
    }

    @Override
    public void onLastfmError(String message, Integer code) {
        mHomeActivityView.hideProgressBar();
    }
}
