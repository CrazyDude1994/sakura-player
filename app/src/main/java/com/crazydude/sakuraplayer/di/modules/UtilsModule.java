package com.crazydude.sakuraplayer.di.modules;

import android.content.Context;

import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.common.Utils;
import com.squareup.otto.Bus;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Crazy on 04.10.2015.
 */
@Module
public class UtilsModule {

    @Provides
    public Constants provideConstants() {
        return new Constants();
    }

    @Provides
    public Utils provideUtils(@Named("Application") Context context, Bus bus) {
        return new Utils(context, bus);
    }
}
