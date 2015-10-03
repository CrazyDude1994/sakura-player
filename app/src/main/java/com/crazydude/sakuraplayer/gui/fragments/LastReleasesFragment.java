package com.crazydude.sakuraplayer.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;
import com.crazydude.sakuraplayer.views.fragments.LastReleasesFragmentView;

import javax.inject.Inject;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
public class LastReleasesFragment extends BaseFragment implements Callbacks.OnResponseListener<AlbumResponse> {

    @Inject
    LastReleasesFragmentView mLastReleasesFragmentView;

    @Inject
    LastfmApiManager mLastfmApiManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initLastReleases();
        return inflater.inflate(R.layout.fragment_last_releases, container, false);
    }

    void initLastReleases() {
        mLastfmApiManager.getNewReleases(null, this);
        mLastReleasesFragmentView.showProgressBar();
        mLastReleasesFragmentView.hideToolbarShadow();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLastReleasesFragmentView.hideProgressBar();
    }

    @Override
    public void onSuccess(AlbumResponse response) {
        mLastReleasesFragmentView.hideProgressBar();
        mLastReleasesFragmentView.setData(response);
    }

    @Override
    public void onLastfmError(String message, Integer code) {
        mLastReleasesFragmentView.hideProgressBar();
    }

    @Override
    public void onNetworkError(String message) {
        mLastReleasesFragmentView.hideProgressBar();
    }
}
