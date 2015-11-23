package com.crazydude.sakuraplayer.managers;

import android.content.SharedPreferences;

/**
 * Created by Crazy on 14.10.2015.
 */
public class PreferencesManager {

    private SharedPreferences mPreferences;

    private static final String KEY_TUTORIAL_COMPLETED = "tutorial_completed";
    private static final String KEY_LASTMFM_TOKEN = "lastfm_token";

    public PreferencesManager(SharedPreferences preferences) {
        mPreferences = preferences;
    }

    public boolean isTutorialCompleted() {
        return mPreferences.getBoolean(KEY_TUTORIAL_COMPLETED, false);
    }

    public void completeTutorial() {
        mPreferences.edit().putBoolean(KEY_TUTORIAL_COMPLETED, true).commit();
    }

    public void saveToken(String token) {
        mPreferences.edit().putString(KEY_LASTMFM_TOKEN, token).commit();
    }

    public String getToken() {
        return mPreferences.getString(KEY_LASTMFM_TOKEN, "");
    }
}
