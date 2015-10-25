package com.crazydude.sakuraplayer.di.modules;

import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.common.Utils;

import javax.inject.Singleton;

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
    public Utils provideUtils() {
        return new Utils();
    }
}
