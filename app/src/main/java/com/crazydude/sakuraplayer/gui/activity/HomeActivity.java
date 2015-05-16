package com.crazydude.sakuraplayer.gui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.services.PlayerService_;
import com.crazydude.sakuraplayer.views.activities.HomeActivityView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_home)
public class HomeActivity extends FragmentActivity implements Callbacks.OnAfterSplashScreenListener {

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    @Bean
    HomeActivityView mHomeActivityView;

    @AfterViews
    void initViews() {
        mHomeActivityView.setOnAfterSplashScreenListener(this);
        mHomeActivityView.hideSplashScreen(Constants.SPLASH_DURATION);
    }

    @Override
    public void onAfterSplashScreen() {
        PlayerService_.intent(this).start();
//        Intent intent = new Intent(this, PlayerService_.class); // note the underscore
//        bindService(intent, mPlayerServiceConnection, Context.BIND_AUTO_CREATE);
        //ArrayList<String> tracks = mMusicLibraryManager.getAllTracks();
        //HashSet<String> tracks = mMusicLibraryManager.getArtistList();
//        getFragmentManager().beginTransaction()
//                .replace(R.id.activity_home_placeholder, PlayerFragment_.builder().build())
//                .commit();
        startActivity(new Intent(this, TracklistActivity_.class));
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.activity_home_placeholder, new PlayerFragment_().builder().build())
//                .commit();
    }
}
