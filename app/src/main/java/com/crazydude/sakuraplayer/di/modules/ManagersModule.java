package com.crazydude.sakuraplayer.di.modules;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.crazydude.navigationhandler.NavigationHandler;
import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.managers.MusicPlayerManager;
import com.crazydude.sakuraplayer.managers.PlayerBinder;
import com.crazydude.sakuraplayer.managers.PreferencesManager;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.services.PlayerService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Crazy on 04.10.2015.
 */
@Module
public class ManagersModule {

    @Provides
    public LastfmApiManager provideLastfmApiManager() {
        return new LastfmApiManager();
    }

    @Provides
    public MusicLibraryManager provideMusicLibraryManager() {
        return new MusicLibraryManager();
    }

    @Provides
    public MusicPlayerManager provideMusicPlayerManager() {
        return new MusicPlayerManager();
    }

    @Provides
    public PlayerBinder providePlayerBinder(PlayerService playerService) {
        return new PlayerBinder(playerService);
    }

    @Provides
    public TrackProvider provideTrackProvider() {
        return new TrackProvider();
    }

    @Provides
    public PreferencesManager providePreferencesManager(SharedPreferences preferences) {
        return new PreferencesManager(preferences);
    }

    @Provides
    public NavigationHandler provideNavigationHandler(AppCompatActivity activity) {
        return new NavigationHandler(activity, R.id.activity_home_placeholder);
    }
}
