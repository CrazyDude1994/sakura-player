package com.crazydude.sakuraplayer.views.fragments;

import android.widget.Button;

import com.crazydude.sakuraplayer.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Crazy on 15.05.2015.
 */
@EBean
public class PlayerView {

    @ViewById(R.id.fragment_player_seekbar)
    DiscreteSeekBar mDiscreteSeekBar;

    @AfterViews
    void initViews() {
    }
}
