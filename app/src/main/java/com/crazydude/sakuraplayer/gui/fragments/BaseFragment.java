package com.crazydude.sakuraplayer.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.di.components.ActivityComponent;
import com.crazydude.sakuraplayer.gui.activity.BaseActivity;
import com.crazydude.sakuraplayer.interfaces.SwitchableFragment;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by CrazyDude on 15.03.2015.
 */
abstract public class BaseFragment extends android.support.v4.app.Fragment implements SwitchableFragment {

    @Inject
    Bus mBus;

    protected ActivityComponent getActivityComponent() {
        return ((BaseActivity) getActivity()).getActivityComponent();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBus.register(this);
    }

    @Override
    public void onPause() {
        mBus.unregister(this);
        super.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutRes(), container, false);
        initViews(rootView);
        return rootView;
    }

    abstract protected int getLayoutRes();

    abstract protected void initViews(View rootView);
}
