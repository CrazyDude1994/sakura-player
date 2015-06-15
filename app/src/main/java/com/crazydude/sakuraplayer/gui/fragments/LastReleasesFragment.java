package com.crazydude.sakuraplayer.gui.fragments;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;
import com.crazydude.sakuraplayer.views.fragments.LastReleasesFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
@EFragment(R.layout.fragment_last_releases)
public class LastReleasesFragment extends BaseFragment implements Callbacks.OnResponseListener<AlbumResponse> {

    @Bean
    LastReleasesFragmentView mLastReleasesFragmentView;

    @Bean
    LastfmApiManager mLastfmApiManager;

    @AfterViews
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
