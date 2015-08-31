package com.crazydude.sakuraplayer.common;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.fragments.ArtistFragment_;
import com.crazydude.sakuraplayer.gui.fragments.LastReleasesFragment_;
import com.crazydude.sakuraplayer.gui.fragments.LastfmArtistFragment_;
import com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialFragment_;
import com.crazydude.sakuraplayer.gui.fragments.PlayerFragment;
import com.crazydude.sakuraplayer.gui.fragments.PlayerFragment_;
import com.crazydude.sakuraplayer.gui.fragments.RecommendsFragment_;
import com.crazydude.sakuraplayer.gui.fragments.TracklistFragment_;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.SystemService;

/**
 * Created by kartavtsev.s on 31.08.2015.
 */
@EBean
public class NavigationHandler implements FragmentManager.OnBackStackChangedListener {

    @RootContext
    AppCompatActivity mContext;

    FragmentManager mFragmentManager;

    private String CURRENT_FRAGMENT = "";

    @AfterInject
    public void init() {
        mFragmentManager = mContext.getSupportFragmentManager();
    }

    public void switchToLastfmTutorial() {
        Fragment fragment = mFragmentManager.findFragmentByTag("LASTFM_TUTORIAL_FRAGMENT");
        if (fragment == null) {
            fragment = LastfmTutorialFragment_.builder().build();
        }
        mFragmentManager.beginTransaction()
                .replace(R.id.activity_home_placeholder, fragment, "LASTFM_TUTORIAL_FRAGMENT")
                .commit();
        CURRENT_FRAGMENT = "LASTFM_TUTORIAL_FRAGMENT";
    }

    public void switchToTracklist() {
        Fragment fragment = mFragmentManager.findFragmentByTag("TRACKLIST_MAIN");
        if (fragment == null) {
            fragment = TracklistFragment_.builder().build();
        }
        mFragmentManager.beginTransaction()
                .replace(R.id.activity_home_placeholder, fragment, "TRACKLIST_MAIN")
                .commit();
        CURRENT_FRAGMENT = "TRACKLIST_MAIN";
    }

    public void switchToRecommendations() {
        Fragment fragment = mFragmentManager.findFragmentByTag("LASTFM_RECOMMENDS");
        if (fragment == null) {
            fragment = RecommendsFragment_.builder().build();
        }
        mFragmentManager.beginTransaction()
                .replace(R.id.activity_home_placeholder, fragment, "LASTFM_RECOMMENDS")
                .commit();
        CURRENT_FRAGMENT = "LASTFM_RECOMMENDS";
    }

    public void switchToLastReleases() {
        Fragment fragment = mFragmentManager.findFragmentByTag("LAST_RELEASES");
        if (fragment == null) {
            fragment = LastReleasesFragment_.builder().build();
        }
        mFragmentManager.beginTransaction()
                .replace(R.id.activity_home_placeholder, fragment, "LAST_RELEASES")
                .commit();
        CURRENT_FRAGMENT = "LAST_RELEASES";
    }

    public void switchToArtistInfo(String name, String mbid) {
        Fragment fragment = mFragmentManager.findFragmentByTag("ARTIST_INFO");
        if (fragment == null) {
            fragment = LastfmArtistFragment_.builder().artistName(name).mbid(mbid).build();
        }
        mFragmentManager.beginTransaction()
                .add(R.id.activity_home_placeholder, fragment, "ARTIST_INFO")
                .addToBackStack(null)
                .commit();
        CURRENT_FRAGMENT = "ARTIST_INFO";
    }

    public void switchToPlayer(TrackModel data) {
        Fragment fragment = mFragmentManager.findFragmentByTag("PLAYER");
        if (fragment == null) {
            fragment = PlayerFragment_.builder().build();
        }
        mFragmentManager.beginTransaction()
                .add(R.id.activity_home_placeholder, fragment, "PLAYER")
                .addToBackStack(null)
                .commit();
        if (data != null) {
            ((PlayerFragment) fragment).setData(data);
        }
        CURRENT_FRAGMENT = "PLAYER";
    }

    public void switchToArtistTracklist(ArtistModel artistModel) {
        Fragment fragment = mFragmentManager.findFragmentByTag("ARTIST_TRACKLIST");
        if (fragment == null) {
            fragment = ArtistFragment_.builder().artistName(artistModel.getArtistName()).build();
        }
        mFragmentManager.beginTransaction()
                .add(R.id.activity_home_placeholder, fragment, "ARTIST_TRACKLIST")
                .addToBackStack(null)
                .commit();
        CURRENT_FRAGMENT = "ARTIST_TRACKLIST";
    }

    @Override
    public void onBackStackChanged() {

    }

    public void onBackPressed() {

    }
}
