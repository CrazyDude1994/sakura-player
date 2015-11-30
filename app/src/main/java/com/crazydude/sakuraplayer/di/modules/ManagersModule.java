package com.crazydude.sakuraplayer.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.crazydude.navigationhandler.NavigationHandler;
import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.managers.MusicPlayerManager;
import com.crazydude.sakuraplayer.managers.PlayerBinder;
import com.crazydude.sakuraplayer.managers.PreferencesManager;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.services.PlayerService;
import com.squareup.otto.Bus;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Crazy on 04.10.2015.
 */
@Module
public class ManagersModule {

    @Provides
    public LastfmApiManager provideLastfmApiManager(@Named("Application") Context context, Utils utils, Bus bus,
                                                    PreferencesManager preferencesManager) {
        return new LastfmApiManager(context, utils, bus, preferencesManager);
    }

    @Provides
    public MusicLibraryManager provideMusicLibraryManager(@Named("Application") Context context, Bus bus) {
        return new MusicLibraryManager(context, bus);
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
    public TrackProvider provideTrackProvider(MusicLibraryManager musicLibraryManager, Bus bus,
                                              @Named("Application") Context context) {
        return new TrackProvider(musicLibraryManager, bus, context);
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
