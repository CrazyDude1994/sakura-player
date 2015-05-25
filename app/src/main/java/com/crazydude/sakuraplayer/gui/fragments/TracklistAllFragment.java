package com.crazydude.sakuraplayer.gui.fragments;

import android.util.Log;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.activity.BaseActivity;
import com.crazydude.sakuraplayer.gui.activity.HomeActivity;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.views.fragments.TracklistAllFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import java.util.List;

/**
 * Created by CrazyDude on 13.04.2015.
 */
@EFragment(R.layout.fragment_tracklist_alltracks)
public class TracklistAllFragment extends BaseFragment implements Callbacks.OnTracksLoadedListener,
        Callbacks.RecyclerViewClickListener {

    @Bean
    TracklistAllFragmentView mTracklistAllFragmentView;

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    @Bean
    TrackProvider mTrackProvider;

    private PlayerFragment mPlayerFragment;

    @AfterViews
    void initViews() {
        mTrackProvider.loadAllTracks(this);
    }

    @UiThread
    @Override
    public void onTrackLoaded(List<TrackModel> tracks) {
        mTracklistAllFragmentView.setTrackList(tracks);
        mTracklistAllFragmentView.setOnRecyclerClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        Log.d("TracklistAllFragment", "Clicked " + position);

        if (mPlayerFragment == null) {
            mPlayerFragment = PlayerFragment_.builder().build();
        }

        mPlayerFragment.playMusic();

        ((BaseActivity) getActivity()).addFragment(mPlayerFragment, true,
                R.id.activity_home_placeholder);
    }
}
