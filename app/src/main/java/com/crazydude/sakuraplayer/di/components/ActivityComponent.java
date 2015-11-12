package com.crazydude.sakuraplayer.di.components;

import com.crazydude.sakuraplayer.di.modules.ActivityModule;
import com.crazydude.sakuraplayer.gui.activity.BaseActivity;
import com.crazydude.sakuraplayer.gui.activity.HomeActivity;
import com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialFragment;
import com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialTextFragment;
import com.crazydude.sakuraplayer.gui.fragments.TracklistAllFragment;
import com.crazydude.sakuraplayer.gui.fragments.TracklistArtistFragment;
import com.crazydude.sakuraplayer.gui.fragments.TracklistFragment;
import com.crazydude.sakuraplayer.views.activities.HomeActivityView;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialFragmentView;
import com.crazydude.sakuraplayer.views.fragments.TracklistAllFragmentView;
import com.crazydude.sakuraplayer.views.fragments.TracklistArtistFragmentView;

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

}
