package com.crazydude.sakuraplayer.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Provides;

/**
 * Created by Crazy on 27.09.2015.
 */
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    public Context provideContext() {
        return mApplication;
    }
}
