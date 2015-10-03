package com.crazydude.sakuraplayer.views.fragments;

import android.widget.TextView;

import com.crazydude.sakuraplayer.R;

import butterknife.Bind;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
public class LastfmTutorialTextFragmentView extends BaseFragmentView {

    @Bind(R.id.fragment_lastfm_tutorial_text_text)
    TextView mTutorialText;

    public void setTutorialText(String tutorialText) {
        if (tutorialText != null) {
            this.mTutorialText.setText(tutorialText);
        }
    }
}
