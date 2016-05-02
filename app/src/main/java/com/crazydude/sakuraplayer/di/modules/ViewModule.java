package com.crazydude.sakuraplayer.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.crazydude.sakuraplayer.views.activities.HomeActivityView;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialFragmentView;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialTextFragmentView;
import com.crazydude.sakuraplayer.views.fragments.RecommendsFragmentView;

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
    public RecommendsFragmentView provideRecommendsFragmentView() {
        return new RecommendsFragmentView();
    }
}
