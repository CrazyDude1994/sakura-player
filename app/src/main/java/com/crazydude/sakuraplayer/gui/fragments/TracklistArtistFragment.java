package com.crazydude.sakuraplayer.gui.fragments;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.views.fragments.TracklistArtistFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Crazy on 27.05.2015.
 */
@EFragment(R.layout.fragment_tracklist_artist)
public class TracklistArtistFragment extends BaseFragment implements Callbacks.OnArtistsLoadedListener {

    @Bean
    TracklistArtistFragmentView mTracklistArtistFragmentView;

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    @Bean
    TrackProvider mTrackProvider;

    @AfterViews
    void initViews() {
        mTrackProvider.loadAllArtists(this);
    }

    @Override
    public void onArtistsLoaded(ArrayList<ArtistModel> artists) {
        mTracklistArtistFragmentView.setArtistList(artists);
    }
}
