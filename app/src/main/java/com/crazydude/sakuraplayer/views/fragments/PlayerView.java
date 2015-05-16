package com.crazydude.sakuraplayer.views.fragments;

import android.widget.Button;

import com.crazydude.sakuraplayer.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Crazy on 15.05.2015.
 */
@EBean
public class PlayerView {

    @ViewById(R.id.fragment_player_button_play)
    Button mPlayButton;

}
