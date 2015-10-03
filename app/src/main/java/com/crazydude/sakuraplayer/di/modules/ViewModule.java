package com.crazydude.sakuraplayer.di.modules;

import android.content.Context;

import com.crazydude.sakuraplayer.views.activities.HomeActivityView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Crazy on 27.09.2015.
 */
@Module
public class ViewModule {

    @Provides
    public HomeActivityView provideHomeActivityView(Context context) {
        return new HomeActivityView();
    }
}
