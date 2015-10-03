package com.crazydude.sakuraplayer.di.modules;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Crazy on 27.09.2015.
 */
@Module
public class ActivityModule {

    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    public Context provideContext() {
        return mActivity;
    }
}
