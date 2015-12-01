package com.crazydude.sakuraplayer.di.modules;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import com.crazydude.sakuraplayer.adapters.ArtistAdapter;
import com.crazydude.sakuraplayer.adapters.TracklistAdapter;

import javax.inject.Named;

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
    public ArtistAdapter provideArtistAdapter(@Named("Activity") Context context) {
        return new ArtistAdapter(context);
    }

    @Provides
    @Named("Artist")
    public CursorLoader provideArtistCursorLoader(@Named("Activity") Context context) {
        return new CursorLoader(context, MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null, null, null);
    }

    @Provides
    @Named("Track")
    public CursorLoader provideTrackCursorLoader(@Named("Activity") Context context) {
        String selection = MediaStore.Audio.Media.IS_MUSIC + " = ?";

        String[] selectionArgs = {
                "1"
        };

        return new CursorLoader(context,
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, selection, selectionArgs, null);
    }
}
