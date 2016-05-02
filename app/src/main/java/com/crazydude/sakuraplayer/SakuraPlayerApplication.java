package com.crazydude.sakuraplayer;

import android.app.Application;

import com.crazydude.sakuraplayer.di.components.ApplicationComponent;
import com.crazydude.sakuraplayer.di.components.DaggerApplicationComponent;
import com.crazydude.sakuraplayer.di.modules.ApplicationModule;
import com.crazydude.sakuraplayer.di.modules.UtilsModule;
import com.crazydude.sakuraplayer.events.RequestUpdateLibraryEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryCompletedEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryStartedEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import lombok.Getter;
import lombok.experimental.Accessors;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
@Getter
@Accessors(prefix = "m")
public class SakuraPlayerApplication extends Application {

    @Inject
    Bus mBus;

    private boolean mIsSplashscreenShown = false;
    private ApplicationComponent mApplicationComponent;
    private boolean mLibraryUpdating;

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
        mApplicationComponent.inject(this);

        mBus.register(this);
    }

    @Produce
    public UpdateLibraryStartedEvent produceTracklistUpdate() {
        if (isLibraryUpdating()) {
            return new UpdateLibraryStartedEvent();
        } else {
            return null;
        }
    }

    @Subscribe
    public void onLibraryUpdate(RequestUpdateLibraryEvent event) {
        mLibraryUpdating = true;
    }

    @Subscribe
    public void onLibraryUpdated(UpdateLibraryCompletedEvent event) {
        mLibraryUpdating = false;
    }

    public boolean isIsSplashscreenShown() {
        return mIsSplashscreenShown;
    }

    public void setIsSplashscreenShown(boolean mIsSplashscreenShown) {
        this.mIsSplashscreenShown = mIsSplashscreenShown;
    }

    public void setIsLibraryUpdating(boolean updating) {
        mLibraryUpdating = updating;
    }

    public boolean isLibraryUpdating() {
        return mLibraryUpdating;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
