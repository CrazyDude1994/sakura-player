package com.crazydude.sakuraplayer.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Crazy on 27.09.2015.
 */
@Module(includes = {AdaptersModule.class, ManagersModule.class, ViewModule.class})
public class ActivityModule {

    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    @Named("Activity")
    public Context provideContext() {
        return mActivity;
    }

    @Provides
    public AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    public SharedPreferences provideSharedPreferences() {
        return mActivity.getApplication().getSharedPreferences("Settings", Context.MODE_PRIVATE);
    }
}
