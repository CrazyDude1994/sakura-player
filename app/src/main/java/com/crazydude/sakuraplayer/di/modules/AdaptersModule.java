package com.crazydude.sakuraplayer.di.modules;

import com.crazydude.sakuraplayer.adapters.ArtistAdapter;
import com.crazydude.sakuraplayer.adapters.TracklistAdapter;
import com.crazydude.sakuraplayer.gui.views.TrackView;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Crazy on 04.10.2015.
 */
@Module
public class AdaptersModule {

    @Provides
    public TracklistAdapter provideTracklistAdapter() {
        return new TracklistAdapter();
    }

    @Provides
    public ArtistAdapter provideArtistAdapter() {
        return new ArtistAdapter();
    }
}
