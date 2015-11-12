package com.crazydude.sakuraplayer.di.modules;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Named;
import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Crazy on 27.09.2015.
 */
@Module(includes = UtilsModule.class)
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Named("Application")
    public Context provideContext() {
        return mApplication;
    }

    @Provides
    public Bus provideBus() {
        return new Bus(ThreadEnforcer.MAIN);
    }
}
