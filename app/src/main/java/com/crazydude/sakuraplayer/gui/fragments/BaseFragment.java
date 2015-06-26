package com.crazydude.sakuraplayer.gui.fragments;

import com.crazydude.sakuraplayer.providers.BusProvider;

/**
 * Created by CrazyDude on 15.03.2015.
 */
public class BaseFragment extends android.support.v4.app.Fragment {

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        BusProvider.getInstance().unregister(this);
        super.onPause();
    }
}
