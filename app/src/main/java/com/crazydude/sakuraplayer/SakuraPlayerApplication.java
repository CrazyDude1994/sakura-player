package com.crazydude.sakuraplayer;

import org.androidannotations.annotations.EApplication;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
@EApplication
public class SakuraPlayerApplication extends com.activeandroid.app.Application {

    private boolean mIsSplashscreenShown = false;

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    public void setIsSplashscreenShown(boolean mIsSplashscreenShown) {
        this.mIsSplashscreenShown = mIsSplashscreenShown;
    }

    public boolean isIsSplashscreenShown() {
        return mIsSplashscreenShown;
    }
}
