package com.crazydude.sakuraplayer.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;
import com.crazydude.sakuraplayer.views.fragments.LastReleasesFragmentView;

import javax.inject.Inject;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
public class LastReleasesFragment extends BaseFragment {

    @Inject
    LastReleasesFragmentView mLastReleasesFragmentView;

    @Inject
    LastfmApiManager mLastfmApiManager;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_last_releases;
    }

    @Override
    protected void initViews(View rootView) {
        mLastfmApiManager.getNewReleases(null);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

/*    @Override
    public void onSuccess(AlbumResponse response) {
        mLastReleasesFragmentView.setData(response);
    }*/

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}
