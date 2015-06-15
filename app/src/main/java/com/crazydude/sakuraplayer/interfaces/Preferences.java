package com.crazydude.sakuraplayer.interfaces;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by kartavtsev.s on 29.05.2015.
 */
@SharedPref(SharedPref.Scope.APPLICATION_DEFAULT)
public interface Preferences {

    @DefaultBoolean(false)
    boolean isTutorialCompleted();

    @DefaultBoolean(true)
    boolean isFirstLaunch();

    @DefaultString("")
    String lastfmToken();

    @DefaultString("")
    String lastfmUsername();
}
