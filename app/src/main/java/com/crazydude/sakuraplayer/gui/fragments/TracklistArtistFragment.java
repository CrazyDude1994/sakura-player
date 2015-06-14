package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.views.fragments.TracklistArtistFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Crazy on 27.05.2015.
 */
@EFragment(R.layout.fragment_tracklist_artist)
public class TracklistArtistFragment extends BaseFragment implements
        Callbacks.OnArtistsLoadedListener, Callbacks.RecyclerViewClickListener {

    @Bean
    TracklistArtistFragmentView mTracklistArtistFragmentView;

    @Bean
    MusicLibraryManager mMusicLibraryManager;

    @Bean
    TrackProvider mTrackProvider;

    private Callbacks.OnSelectedArtistListener mOnSelectedArtistListener;
    private ArrayList<ArtistModel> mArtistModels;

    @AfterViews
    void initViews() {
        mTrackProvider.loadAllArtists(this);
    }

    @UiThread
    @Override
    public void onArtistsLoaded(ArrayList<ArtistModel> artists) {
        mArtistModels = artists;
        mTracklistArtistFragmentView.setArtistList(artists);
        mTracklistArtistFragmentView.setOnRecyclerClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        mOnSelectedArtistListener.onSelectedArtist(mArtistModels.get(position));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnSelectedArtistListener = (Callbacks.OnSelectedArtistListener) activity;
    }
}
