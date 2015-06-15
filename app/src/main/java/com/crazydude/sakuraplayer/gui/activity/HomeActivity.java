package com.crazydude.sakuraplayer.gui.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.view.MenuItem;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.SakuraPlayerApplication;
import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.gui.fragments.ArtistFragment_;
import com.crazydude.sakuraplayer.gui.fragments.LastReleasesFragment_;
import com.crazydude.sakuraplayer.gui.fragments.LastfmArtistFragment_;
import com.crazydude.sakuraplayer.gui.fragments.LastfmLoginFragment;
import com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialFragment_;
import com.crazydude.sakuraplayer.gui.fragments.PlayerFragment;
import com.crazydude.sakuraplayer.gui.fragments.PlayerFragment_;
import com.crazydude.sakuraplayer.gui.fragments.RecommendsFragment_;
import com.crazydude.sakuraplayer.gui.fragments.TracklistFragment_;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.interfaces.Preferences_;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.managers.PlayerBinder;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.models.net.SessionResponse;
import com.crazydude.sakuraplayer.services.PlayerService;
import com.crazydude.sakuraplayer.services.PlayerService_;
import com.crazydude.sakuraplayer.views.activities.HomeActivityView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnAfterSplashScreenListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnLastfmLoginListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnLastfmTutorialCompletedListener;
import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnResponseListener;

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements OnAfterSplashScreenListener,
        OnLastfmTutorialCompletedListener, OnLastfmLoginListener,
        OnResponseListener<SessionResponse>, NavigationView.OnNavigationItemSelectedListener,
        Callbacks.OnSelectedLastfmArtistListener, Callbacks.OnSelectedTrackListener, ServiceConnection,
        Callbacks.OnPlayerListener, Callbacks.OnSelectedArtistListener, FragmentManager.OnBackStackChangedListener {

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

    private PlayerFragment mPlayerFragment;
    private PlayerBinder mBinder;
    private PlayerBroadcastReceiver mPlayerBroadcastReceiver;

    @AfterViews
    void initViews() {
        SakuraPlayerApplication application = (SakuraPlayerApplication) getApplication();
        mHomeActivityView.setOnAfterSplashScreenListener(this);
        if (application.isIsSplashscreenShown() == false) {
            mHomeActivityView.hideSplashScreen(Constants.SPLASH_DURATION);
            application.setIsSplashscreenShown(true);
        } else {
            mHomeActivityView.hideSplashScreen();
            onAfterSplashScreen();
        }
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @AfterInject
    void init() {
        startService(PlayerService_.intent(this).get());
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public void onAfterSplashScreen() {
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
        mPrefs.lastfmUsername().put(response.getSession().getName());
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

    private void registerReceiver() {
        if (mPlayerBroadcastReceiver == null) {
            mPlayerBroadcastReceiver = new PlayerBroadcastReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PlayerService.ACTION_PLAY);
        intentFilter.addAction(PlayerService.ACTION_PAUSE);
        intentFilter.addAction(PlayerService.ACTION_RESUME);
        intentFilter.addAction(PlayerService.ACTION_SEEK);
        intentFilter.addAction(PlayerService.ACTION_STOP);
        LocalBroadcastManager.getInstance(this).registerReceiver(mPlayerBroadcastReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mPlayerBroadcastReceiver);
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
                switchFragment(RecommendsFragment_.builder().build(), false,
                        R.id.activity_home_placeholder);
                mHomeActivityView.hidePlayerWidget();
                break;
            case R.id.navigation_menu_new_releases:
                switchFragment(LastReleasesFragment_.builder().build(), false,
                        R.id.activity_home_placeholder);
                mHomeActivityView.hidePlayerWidget();
                break;
        }

        mHomeActivityView.closeNavigationDrawer();
        return true;
    }


    @Override
    public void onSelecteLastfmArtist(String name, String mbid) {
        switchFragment(LastfmArtistFragment_.builder()
                .artistName(name)
                .mbid(mbid)
                .build(), true, R.id.activity_home_placeholder);
    }

    @Override
    public void onSelectedTrack(TrackModel track) {
        if (track != null) {
            switchToPlayerWithData(track);
            mBinder.play(generateSingleTrackPlaylist(track));
        }
    }

    private void switchToPlayerWithData(TrackModel data) {
        mHomeActivityView.hideToolbarShadow();
        if (mPlayerFragment == null) {
            mPlayerFragment = PlayerFragment_.builder().build();
        }
        switchFragment(mPlayerFragment, true, R.id.activity_home_placeholder);
        if (data != null) {
            mPlayerFragment.setData(data);
        }
    }

    private PlaylistModel generateSingleTrackPlaylist(TrackModel model) {
        ArrayList<TrackModel> tracks = new ArrayList<>();
        tracks.add(model);
        return new PlaylistModel(tracks, "Current");
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
        Intent intent = new Intent(this, PlayerService_.class);
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
            mHomeActivityView.setPlayerWidgetData(data.getTrackName(), data.getArtist().getArtistName());
        } else {
            mHomeActivityView.hidePlayerWidget();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver();
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
        switchFragment(ArtistFragment_.builder().artistName(artist.getArtistName()).build(), true,
                R.id.activity_home_placeholder);
    }

    @Override
    public void onBackStackChanged() {
        if (mPlayerFragment != null && mBinder != null && !mPlayerFragment.isVisible()) {
            mHomeActivityView.showPlayerWidget();
        } else {
            mHomeActivityView.hidePlayerWidget();
        }
    }

    @Click(R.id.activity_home_view_player_widget)
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

    private class PlayerBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case PlayerService.ACTION_PLAY:
                    String songName = intent.getStringExtra(PlayerService.EXTRA_SONG_NAME);
                    String artistName = intent.getStringExtra(PlayerService.EXTRA_ARTIST_NAME);
                    mHomeActivityView.setPlayerWidgetData(songName, artistName);
                    break;
                case PlayerService.ACTION_PAUSE:
                    break;
                case PlayerService.ACTION_RESUME:
                    break;
                case PlayerService.ACTION_SEEK:
                    break;
                case PlayerService.ACTION_STOP:
                    mHomeActivityView.hidePlayerWidget();
                    break;
            }
        }
    }
}
