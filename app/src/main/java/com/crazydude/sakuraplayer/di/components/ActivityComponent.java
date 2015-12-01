package com.crazydude.sakuraplayer.di.components;

import android.support.v4.content.CursorLoader;

import com.crazydude.sakuraplayer.di.modules.ActivityModule;
import com.crazydude.sakuraplayer.gui.activity.BaseActivity;
import com.crazydude.sakuraplayer.gui.activity.HomeActivity;
import com.crazydude.sakuraplayer.gui.fragments.ArtistFragment;
import com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialFragment;
import com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialTextFragment;
import com.crazydude.sakuraplayer.gui.fragments.PlayerFragment;
import com.crazydude.sakuraplayer.gui.fragments.RecommendsFragment;
import com.crazydude.sakuraplayer.gui.fragments.TracklistAllFragment;
import com.crazydude.sakuraplayer.gui.fragments.TracklistArtistFragment;
import com.crazydude.sakuraplayer.gui.fragments.TracklistFragment;
import com.crazydude.sakuraplayer.views.fragments.ArtistFragmentView;
import com.crazydude.sakuraplayer.views.fragments.RecommendsFragmentView;
import com.crazydude.sakuraplayer.views.fragments.TracklistAllFragmentView;
import com.crazydude.sakuraplayer.views.fragments.TracklistArtistFragmentView;

import javax.inject.Named;

import dagger.Subcomponent;

/**
 * Created by Crazy on 27.09.2015.
 */
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    void inject(HomeActivity homeActivity);

    void inject(LastfmTutorialFragment lastfmTutorialFragment);

    void inject(LastfmTutorialTextFragment lastfmTutorialTextFragment);

    void inject(TracklistFragment tracklistFragment);

    void inject(TracklistAllFragment tracklistAllFragment);

    void inject(TracklistAllFragmentView mTracklistAllFragmentView);

    void inject(TracklistArtistFragment tracklistArtistFragment);

    void inject(TracklistArtistFragmentView mTracklistArtistFragmentView);

    void inject(PlayerFragment playerFragment);

    void inject(ArtistFragment artistFragment);

    void inject(ArtistFragmentView mArtistFragmentView);

    void inject(RecommendsFragment recommendsFragment);

    void inject(RecommendsFragmentView mRecommendsFragmentView);

    @Named("Track")
    CursorLoader getTrackCursorLoader();
    @Named("Artist")
    CursorLoader getArtistCursorLoader();
}
