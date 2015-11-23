package com.crazydude.sakuraplayer;

import android.app.Application;

import com.crazydude.sakuraplayer.di.components.ApplicationComponent;
import com.crazydude.sakuraplayer.di.components.DaggerApplicationComponent;
import com.crazydude.sakuraplayer.di.modules.ApplicationModule;
import com.crazydude.sakuraplayer.di.modules.UtilsModule;

import lombok.Getter;
import lombok.experimental.Accessors;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
@Getter
@Accessors(prefix = "m")
public class SakuraPlayerApplication extends Application {

    private boolean mIsSplashscreenShown = false;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .utilsModule(new UtilsModule())
                .build();
    }

    public boolean isIsSplashscreenShown() {
        return mIsSplashscreenShown;
    }

    public void setIsSplashscreenShown(boolean mIsSplashscreenShown) {
        this.mIsSplashscreenShown = mIsSplashscreenShown;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
