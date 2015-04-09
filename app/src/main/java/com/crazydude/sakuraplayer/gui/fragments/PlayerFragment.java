package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.mvp.presenters.IPlayerPresenter;
import com.crazydude.sakuraplayer.mvp.presenters.PlayerPresenter;
import com.crazydude.sakuraplayer.mvp.views.PlayerView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_player)
public class PlayerFragment extends Fragment implements PlayerView{

    private IPlayerPresenter playerPresenter;

    @AfterInject
    void init() {
        playerPresenter = new PlayerPresenter();
    }
}
