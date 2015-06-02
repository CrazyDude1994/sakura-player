package com.crazydude.sakuraplayer.common;

import org.androidannotations.annotations.EBean;

/**
 * Created by CrazyDude on 14.03.2015.
 */
@EBean(scope = EBean.Scope.Singleton)
public class Constants {

    public final static int SPLASH_DURATION = 3000;

    public final static String LASTFM_API_ROOT_URL = "https://ws.audioscrobbler.com/2.0/";
    public final static String LASTFM_API_KEY = "30f5f9d26d668aa917f0b76bd3fa5773";
    public static final String LASTFM_API_SECRET = "a4bab0f23cdfe0c0aa3ac0a60b5fef86";
}
