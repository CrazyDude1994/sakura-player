package com.crazydude.sakuraplayer.gui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.gui.fragments.ArtistFragment_;
import com.crazydude.sakuraplayer.gui.fragments.LastfmLoginFragment;
import com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialFragment_;
import com.crazydude.sakuraplayer.gui.fragments.PlayerFragment_;
import com.crazydude.sakuraplayer.gui.fragments.RecommendsFragment_;
import com.crazydude.sakuraplayer.gui.fragments.TracklistFragment_;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.interfaces.Preferences_;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.models.net.SessionResponse;
import com.crazydude.sakuraplayer.services.PlayerService_;
import com.crazydude.sakuraplayer.views.activities.HomeActivityView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnAfterSplashScreenListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnLastfmLoginListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnLastfmTutorialCompletedListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnResponseListener;

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements OnAfterSplashScreenListener,
        OnLastfmTutorialCompletedListener, OnLastfmLoginListener,
        OnResponseListener<SessionResponse>, NavigationView.OnNavigationItemSelectedListener,
        Callbacks.OnSelectedArtistListener, Callbacks.OnSelectedTrackListener {

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    @Bean
    HomeActivityView mHomeActivityView;

    @ViewById(R.id.activity_home_navigation_view)
    NavigationView mNavigationView;

    @Pref
    Preferences_ mPrefs;

    @Bean
    Utils mUtils;

    @Bean
    LastfmApiManager mLastfmApiManager;

    @AfterViews
    void initViews() {
        mHomeActivityView.setOnAfterSplashScreenListener(this);
        mHomeActivityView.hideSplashScreen(Constants.SPLASH_DURATION);
        mNavigationView.setNavigationItemSelectedListener(this);
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

    public HomeActivityView getHomeActivityView() {
        return mHomeActivityView;
    }

    private void switchToPlayerMode() {
        mHomeActivityView.showToolbar();
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
        mLastfmApiManager.login(login, password, this);
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
        mHomeActivityView.showInfoDialog(getString(R.string.network_error), message);
    }

    @Override
    public void onLastfmError(String message, Integer code) {
        mHomeActivityView.hideProgressBar();
        switch (code) {
            case 4:
                mHomeActivityView.showInfoDialog(getString(R.string.error),
                        getString(R.string.invalid_password_or_login));
                break;
            default:
                mHomeActivityView.showInfoDialog(getString(R.string.error), message);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_menu_recommends:
                switchFragment(RecommendsFragment_.builder().build(), false, R.id.activity_home_placeholder);
                break;
        }

        mHomeActivityView.closeNavigationDrawer();
        return true;
    }


    @Override
    public void onSelectedArtist(String name, String mbid) {
        switchFragment(ArtistFragment_.builder()
                .artistName(name)
                .mbid(mbid)
                .build(), true, R.id.activity_home_placeholder);
    }

    @Override
    public void onSelectedTrack(TrackModel track) {
        if (track != null) {
            switchFragment(PlayerFragment_.builder().songPath(track.getTrackPath()).build(), true, R.id.activity_home_placeholder);
        }
    }
}
