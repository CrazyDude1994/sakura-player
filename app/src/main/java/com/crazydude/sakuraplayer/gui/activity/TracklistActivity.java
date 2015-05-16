package com.crazydude.sakuraplayer.gui.activity;

import android.support.v4.app.FragmentActivity;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.views.activities.TracklistActivityView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_tracklist)
public class TracklistActivity extends FragmentActivity {


    @Bean
    TracklistActivityView mTracklistActivityView;

    @AfterViews
    void initViews() {

    }

}
