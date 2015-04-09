package com.crazydude.sakuraplayer.managers;

import com.crazydude.sakuraplayer.interfaces.LastfmInterface;

import org.androidannotations.annotations.EBean;

/**
 * Created by CrazyDude on 15.03.2015.
 */
@EBean(scope = EBean.Scope.Singleton)
public class LastfmApiManager {

    LastfmInterface lastfmApi;
}
