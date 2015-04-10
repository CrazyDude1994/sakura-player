package com.crazydude.sakuraplayer.mvp.models;

import com.crazydude.sakuraplayer.mvp.presenters.IPlayerPresenter;

/**
 * Created by Serega on 10.04.2015.
 */
public class PlayerModel implements IPlayerModel {

    private IPlayerPresenter mPlayerPresenter;

    public PlayerModel(IPlayerPresenter playerPresenter) {
        mPlayerPresenter = playerPresenter;
    }

}
