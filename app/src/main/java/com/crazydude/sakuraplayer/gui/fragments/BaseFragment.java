package com.crazydude.sakuraplayer.gui.fragments;

import android.os.Bundle;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by CrazyDude on 15.03.2015.
 */
public class BaseFragment extends android.support.v4.app.Fragment {

    @Inject
    Bus mBus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, getActivity());
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
}
