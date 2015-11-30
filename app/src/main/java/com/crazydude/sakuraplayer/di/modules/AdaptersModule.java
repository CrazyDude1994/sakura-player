package com.crazydude.sakuraplayer.di.modules;

import com.crazydude.sakuraplayer.adapters.ArtistAdapter;
import com.crazydude.sakuraplayer.adapters.TracklistAdapter;
import com.crazydude.sakuraplayer.providers.TrackProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Crazy on 04.10.2015.
 */
@Module
public class AdaptersModule {

    @Provides
    public TracklistAdapter provideTracklistAdapter(TrackProvider trackProvider) {
        return new TracklistAdapter(trackProvider.getTracklistCursor());
    }

    @Provides
    public ArtistAdapter provideArtistAdapter(TrackProvider trackProvider) {
        return new ArtistAdapter(trackProvider.getArtistCursor());
    }
}
