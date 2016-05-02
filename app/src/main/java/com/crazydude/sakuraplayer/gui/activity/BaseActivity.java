package com.crazydude.sakuraplayer.gui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crazydude.sakuraplayer.SakuraPlayerApplication;
import com.crazydude.sakuraplayer.di.components.ActivityComponent;
import com.crazydude.sakuraplayer.di.components.ApplicationComponent;
import com.crazydude.sakuraplayer.di.components.DaggerApplicationComponent;
import com.crazydude.sakuraplayer.di.modules.ActivityModule;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import lombok.Getter;
import lombok.experimental.Accessors;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Crazy on 24.05.2015.
 */
@Getter
@Accessors(prefix = "m")
abstract public class BaseActivity extends AppCompatActivity {

    @Inject
    Bus mBus;

    ActivityComponent mActivityComponent;

    public SakuraPlayerApplication getSakuraPlayerApplication() {
        return (SakuraPlayerApplication) getApplication();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBus.register(this);
    }

    @Override
    protected void onPause() {
        mBus.unregister(this);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    protected void injectDependencies() {
        mActivityComponent = getSakuraPlayerApplication()
                .getApplicationComponent()
                .provideActivityComponent(new ActivityModule(this));
        mActivityComponent.inject(this);
    }
}
