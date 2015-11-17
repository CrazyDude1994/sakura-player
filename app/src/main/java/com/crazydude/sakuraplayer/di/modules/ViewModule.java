package com.crazydude.sakuraplayer.di.modules;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.crazydude.sakuraplayer.views.activities.HomeActivityView;
import com.crazydude.sakuraplayer.views.fragments.ArtistFragmentView;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialFragmentView;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialTextFragmentView;
import com.crazydude.sakuraplayer.views.fragments.PlayerView;
import com.crazydude.sakuraplayer.views.fragments.TracklistAllFragmentView;
import com.crazydude.sakuraplayer.views.fragments.TracklistArtistFragmentView;
import com.crazydude.sakuraplayer.views.fragments.TracklistFragmentView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Crazy on 27.09.2015.
 */
@Module
public class ViewModule {

    @Provides
    public HomeActivityView provideHomeActivityView(AppCompatActivity activity) {
        return new HomeActivityView(activity);
    }

    @Provides
    public LastfmTutorialFragmentView provideLastfmTutorialFragmentView(AppCompatActivity activity) {
        return new LastfmTutorialFragmentView(activity);
    }

    @Provides
    public LastfmTutorialTextFragmentView provideLastfmTutorialTextFragmentView() {
        return new LastfmTutorialTextFragmentView();
    }

    @Provides
    public TracklistFragmentView provideTracklistFragmentView() {
        return new TracklistFragmentView();
    }

    @Provides
    public TracklistAllFragmentView provideTracklistAllFragmentView() {
        return new TracklistAllFragmentView();
    }

    @Provides
    public TracklistArtistFragmentView provideTracklistArtistFragmentView() {
        return new TracklistArtistFragmentView();
    }

    @Provides
    public PlayerView providePlayerView() {
        return new PlayerView();
    }

    @Provides
    public ArtistFragmentView provideArtistFragmentView() {
        return new ArtistFragmentView();
    }
}
