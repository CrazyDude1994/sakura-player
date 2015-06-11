package com.crazydude.sakuraplayer;

import android.app.Application;
import android.content.Context;

import com.activeandroid.query.Select;
import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.EApplication;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
@EApplication
public class SakuraPlayerApplication extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
