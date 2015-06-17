package com.crazydude.sakuraplayer.gui.fragments;

import android.support.v4.app.Fragment;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.views.fragments.TracklistFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.List;

/**
 * Created by Crazy on 25.05.2015.
 */
@EFragment(R.layout.fragment_tracklist)
public class TracklistFragment extends BaseFragment implements Callbacks.Updatable {

    @Bean
    TracklistFragmentView mTracklistFragmentView;

    @Bean
    LastfmApiManager mLastfmApiManager;

    @AfterViews
    void initViews() {
        mTracklistFragmentView.initViewPager(this);
    }

    @Override
    public void onUpdate() {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment instanceof Callbacks.Updatable) {
                    ((Callbacks.Updatable) fragment).onUpdate();
                }
            }
        }
    }
}
