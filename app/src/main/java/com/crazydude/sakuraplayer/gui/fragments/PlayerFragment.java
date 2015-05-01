package com.crazydude.sakuraplayer.gui.fragments;

import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.mvp.models.PlayerModelImpl;
import com.crazydude.sakuraplayer.mvp.models.interfaces.PlayerModel;
import com.crazydude.sakuraplayer.mvp.presenters.interfaces.PlayerPresenter;
import com.crazydude.sakuraplayer.mvp.presenters.PlayerPresenterImpl;
import com.crazydude.sakuraplayer.mvp.views.interfaces.PlayerView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_player)
public class PlayerFragment extends BaseFragment implements PlayerView {

    private PlayerPresenter mPlayerPresenter;


    @AfterInject
    void init() {
       // mPlayerPresenter = new PlayerPresenterImpl(getActivity(), this, new PlayerModelImpl());
    }

    @Click({R.id.fragment_player_button_play, R.id.fragment_player_button_next,
            R.id.fragment_player_button_prev})
    void onClick(View v) {
        mPlayerPresenter.onClick(v);
    }

    @Override
    public void onStop() {
        mPlayerPresenter.onStop();
        super.onStop();
    }
}