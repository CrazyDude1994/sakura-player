package com.crazydude.sakuraplayer.gui.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MenuItem;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.SakuraPlayerApplication;
import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.events.PlayerEvent;
import com.crazydude.sakuraplayer.features.Feature;
import com.crazydude.sakuraplayer.features.FeatureIsNullException;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.NavigationDrawerFeature;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.gui.fragments.LastfmLoginFragment;
import com.crazydude.sakuraplayer.gui.fragments.PlayerFragment;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.interfaces.FeatureProvider;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.managers.NavigationManager;
import com.crazydude.sakuraplayer.managers.PlayerBinder;
import com.crazydude.sakuraplayer.managers.PreferencesManager;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.models.net.SessionResponse;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.services.PlayerService;
import com.crazydude.sakuraplayer.views.activities.HomeActivityView;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.Getter;
import lombok.experimental.Accessors;

import static com.crazydude.sakuraplayer.common.Constants.FragmentsEnum.LastfmTutorialFragment;
import static com.crazydude.sakuraplayer.common.Constants.FragmentsEnum.TracklistFragment;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnAfterSplashScreenListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnLastfmLoginListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnLastfmTutorialCompletedListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnResponseListener;

@Accessors(prefix = "m")
public class HomeActivity extends BaseActivity implements OnAfterSplashScreenListener,
        OnLastfmTutorialCompletedListener, OnLastfmLoginListener,
        OnResponseListener<SessionResponse>, NavigationView.OnNavigationItemSelectedListener,
        Callbacks.OnSelectedLastfmArtistListener, Callbacks.OnSelectedTrackListener, ServiceConnection,
        Callbacks.OnPlayerListener, Callbacks.OnSelectedArtistListener,
        FragmentManager.OnBackStackChangedListener, SwipeRefreshLayout.OnRefreshListener,
        MediaScannerConnection.OnScanCompletedListener, FeatureProvider {

    @Inject
    TrackProvider mTrackProvider;

    @Getter
    @Inject
    HomeActivityView mHomeActivityView;

    @Inject
    NavigationManager mNavigationManager;

    @Bind(R.id.activity_home_navigation_view)
    NavigationView mNavigationView;

    @Inject
    Utils mUtils;

    @Inject
    LastfmApiManager mLastfmApiManager;

    @Inject
    MusicLibraryManager mMusicLibraryManager;

    @Inject
    PreferencesManager mPreferencesManager;

    private PlayerFragment mPlayerFragment;
    private PlayerBinder mBinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        init();
        initViews();
    }

    private void initViews() {
        SakuraPlayerApplication application = (SakuraPlayerApplication) getApplication();
        mHomeActivityView.setOnAfterSplashScreenListener(this);

        /*if (!mPrefs.isTutorialCompleted().get()) {
            mUtils.triggerMediaScan(this);
        }*/

        if (application.isIsSplashscreenShown() == false) {
            mHomeActivityView.hideSplashScreen(Constants.SPLASH_DURATION);
            application.setIsSplashscreenShown(true);
        } else {
            mHomeActivityView.hideSplashScreen();
            onAfterSplashScreen();
        }
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    void init() {
//        startService(PlayerService_.intent(this).get());
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public void onAfterSplashScreen() {
        if (!mPreferencesManager.isTutorialCompleted()) {
            mNavigationManager.switchFragment(LastfmTutorialFragment, true);
        } else {
            switchToPlayerMode();
        }
    }

    private void switchToPlayerMode() {
        mHomeActivityView.showToolbar();
        mNavigationManager.switchFragment(TracklistFragment, false);
    }

    @Override
    public void onTutorialCompleted(boolean isLoginToLastfm) {
//        mPrefs.isTutorialCompleted().put(true);
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
/*        mPrefs.lastfmToken().put(response.getSession().getKey());
        mPrefs.lastfmUsername().put(response.getSession().getName());*/
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
/*                switchFragment(RecommendsFragment_.builder().build(), false,
                        R.id.activity_home_placeholder);*/
                mHomeActivityView.hidePlayerWidget();
                break;
            case R.id.navigation_menu_new_releases:
/*                switchFragment(LastReleasesFragment_.builder().build(), false,
                        R.id.activity_home_placeholder);*/
                mHomeActivityView.hidePlayerWidget();
                break;
        }

        mHomeActivityView.closeNavigationDrawer();
        return true;
    }


    @Override
    public void onSelecteLastfmArtist(String name, String mbid) {
/*        switchFragment(LastfmArtistFragment_.builder()
                .artistName(name)
                .mbid(mbid)
                .build(), true, R.id.activity_home_placeholder);*/
    }

    @Override
    public void onSelectedTrack(TrackModel track) {
        if (track != null) {
            switchToPlayerWithData(track);
            mBinder.play(mUtils.generateSingleTrackPlaylist(track));
        }
    }

    private void switchToPlayerWithData(TrackModel data) {
        mHomeActivityView.hideToolbarShadow();
        if (mPlayerFragment == null) {
            mPlayerFragment = new PlayerFragment();
        }
//        switchFragment(mPlayerFragment, true, R.id.activity_home_placeholder);
        if (data != null) {
            mPlayerFragment.setData(data);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mBinder = (PlayerBinder) service;
        updatePlayerWidget();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mBinder = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PlayerService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBinder != null) {
            unbindService(this);
            mBinder = null;
        }
    }

    private void updatePlayerWidget() {
        if (mBinder != null && mBinder.getCurrentTrack() != null && mBinder.isPlaying()) {
            TrackModel data = mBinder.getCurrentTrack();
            mHomeActivityView.setPlayerWidgetData(data);
        } else {
            mHomeActivityView.hidePlayerWidget();
        }
    }

    @Override
    public void onPauseOrResume() {
        if (mBinder != null) {
            if (mBinder.isPlaying()) {
                mBinder.pause();
            } else {
                mBinder.resume();
            }
        }
    }

    @Override
    public void onNext() {
        if (mBinder != null) {
            mBinder.next();
        }
    }

    @Override
    public void onPrevious() {
        if (mBinder != null) {
            mBinder.previous();
        }
    }

    @Override
    public void onSetPlaylist(PlaylistModel playlist) {
        if (mBinder != null) {
            mBinder.play(playlist);
            switchToPlayerWithData(playlist.getTracks().get(0));
        }
    }

    @Override
    public void onSeek(int progress) {
        mBinder.seek(progress);
    }

    @Override
    public void onSelectedArtist(ArtistModel artist) {
/*        switchFragment(ArtistFragment_.builder().artistName(artist.getArtistName()).build(), true,
                R.id.activity_home_placeholder);*/
    }

    @Override
    public void onBackStackChanged() {
        if (mPlayerFragment != null && mBinder != null && !mPlayerFragment.isVisible()) {
            mHomeActivityView.showPlayerWidget();
        } else {
            mHomeActivityView.hidePlayerWidget();
        }
    }

    @OnClick(R.id.activity_home_view_player_widget)
    void onPlayerWidgetClick() {
        if (mBinder != null) {
            switchToPlayerWithData(mBinder.getCurrentTrack());
        }
    }

    @Override
    public void onSwitchShuffle(boolean isShuffle) {
        if (mBinder != null) {
            mBinder.setShuffleMode(isShuffle);
        }
    }

    @Override
    public void onSwitchRepeat(boolean isRepeat) {
        if (mBinder != null) {
            mBinder.setRepeatMode(isRepeat);
        }
    }

    @Override
    public void onRefresh() {
        mUtils.triggerMediaScan(this);
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        mTrackProvider.updateMusicDatabase();
    }

    @Subscribe
    public void onPlaybackEvent(PlayerEvent.PlayerPlaybackEvent event) {
        switch (event.getAction()) {
            case PLAY:
                mHomeActivityView.setPlayerWidgetData(event.getTrackModel());
                break;
            case STOP:
                mHomeActivityView.hidePlayerWidget();
                break;
        }
    }

    public void provideFeatures(@NonNull Features features) {
        if (features == null) {
            throw new FeatureIsNullException();
        }
        for (Feature feature : features.getFeatures()) {
            switch (feature.getFeatureType()) {
                case TOOLBAR:

                    ToolbarFeature toolbarFeature = (ToolbarFeature) feature;

                    if (toolbarFeature.isSetVisible()) {

                    } else {

                    }

                    if (toolbarFeature.isBackButton()) {
                    }

                    if (toolbarFeature.getTitle() != null) {
                    }

                    if (toolbarFeature.getTitleRes() != null) {
                    }
                    break;

                case NAVIGATION_DRAWER:
                    if (((NavigationDrawerFeature) feature).isEnabled()) {
                    }
            }
            break;
        }
    }

    @Override
    public void onBackPressed() {
        mNavigationManager.handleBackButtonPress();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigationManager.storeCurrentFragment(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mNavigationManager.restoreCurrentFragment(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
