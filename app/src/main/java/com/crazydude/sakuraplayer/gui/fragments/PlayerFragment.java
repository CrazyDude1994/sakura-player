package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Fragment;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.mvp.presenters.IPlayerPresenter;
import com.crazydude.sakuraplayer.mvp.presenters.PlayerPresenter;
import com.crazydude.sakuraplayer.mvp.views.PlayerView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_player)
public class PlayerFragment extends BaseFragment implements PlayerView {

    private IPlayerPresenter mPlayerPresenter;

    @AfterInject
    void init() {
        mPlayerPresenter = new PlayerPresenter(this);
    }
}