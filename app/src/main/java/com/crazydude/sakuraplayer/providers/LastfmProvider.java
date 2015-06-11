package com.crazydude.sakuraplayer.providers;

import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by kartavtsev.s on 11.06.2015.
 */
@EBean
public class LastfmProvider {

    @Bean
    LastfmApiManager mLastfmApiManager;
}
