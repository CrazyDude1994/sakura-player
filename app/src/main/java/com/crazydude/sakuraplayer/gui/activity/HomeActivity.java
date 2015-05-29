package com.crazydude.sakuraplayer.gui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.gui.fragments.LastfmLoginFragment_;
import com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialFragment;
import com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialFragment_;
import com.crazydude.sakuraplayer.gui.fragments.TracklistFragment;
import com.crazydude.sakuraplayer.gui.fragments.TracklistFragment_;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.interfaces.Preferences_;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.services.PlayerService_;
import com.crazydude.sakuraplayer.views.activities.HomeActivityView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements Callbacks.OnAfterSplashScreenListener,
        Callbacks.OnLastfmTutorialCompletedListener{

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    @Bean
    HomeActivityView mHomeActivityView;

    @Pref
    Preferences_ mPrefs;

    @AfterViews
    void initViews() {
        mHomeActivityView.setOnAfterSplashScreenListener(this);
        mHomeActivityView.hideSplashScreen(Constants.SPLASH_DURATION);
    }

    @Override
    public void onAfterSplashScreen() {
        PlayerService_.intent(this).start();
        switchFragment(LastfmTutorialFragment_.builder().build(), false, R.id.activity_home_placeholder);
//        Intent intent = new Intent(this, PlayerService_.class); // note the underscore
//        bindService(intent, mPlayerServiceConnection, Context.BIND_AUTO_CREATE);
        //ArrayList<String> tracks = mMusicLibraryManager.getAllTracks();
        //HashSet<String> tracks = mMusicLibraryManager.getArtistList();
//        getFragmentManager().beginTransaction()
//                .replace(R.id.activity_home_placeholder, PlayerFragment_.builder().build())
//                .commit();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.activity_home_placeholder, new PlayerFragment_().builder().build())
//                .commit();
    }

    @Override
    public void onTutorialCompleted(boolean isLoginToLastfm) {
        mPrefs.isTutorialCompleted().put(true);

        if (isLoginToLastfm) {
            switchFragment(LastfmLoginFragment_.builder().build(), false,
                    R.id.activity_home_placeholder);
        } else {
            switchFragment(TracklistFragment_.builder().build(), false,
                    R.id.activity_home_placeholder);
        }
    }
}
