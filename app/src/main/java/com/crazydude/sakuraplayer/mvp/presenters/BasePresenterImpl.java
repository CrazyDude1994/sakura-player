package com.crazydude.sakuraplayer.mvp.presenters;

import android.content.Context;

import com.crazydude.sakuraplayer.mvp.presenters.interfaces.BasePresenter;

/**
 * Created by Crazy on 26.04.2015.
 */
public class BasePresenterImpl<ViewType, ModelType> implements BasePresenter {

    protected ViewType mPresenterView;
    protected ModelType mModel;
    protected Context mContext;

    public BasePresenterImpl(Context context, ViewType presenterView, ModelType model) {
        this.mPresenterView = presenterView;
        this.mContext = context;
        this.mModel = model;
    }
}
