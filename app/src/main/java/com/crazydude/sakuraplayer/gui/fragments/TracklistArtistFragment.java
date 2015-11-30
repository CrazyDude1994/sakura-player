package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.events.UpdateLibraryCompletedEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryStartedEvent;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.views.fragments.TracklistArtistFragmentView;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Crazy on 27.05.2015.
 */
public class TracklistArtistFragment extends BaseFragment implements
        Callbacks.OnArtistsLoadedListener, Callbacks.RecyclerViewClickListener {

    @Inject
    TracklistArtistFragmentView mTracklistArtistFragmentView;

    @Inject
    MusicLibraryManager mMusicLibraryManager;

    @Inject
    TrackProvider mTrackProvider;

    private Callbacks.OnSelectedArtistListener mOnSelectedArtistListener;
    private ArrayList<ArtistModel> mArtistModels;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tracklist_artist;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        getActivityComponent().inject(mTracklistArtistFragmentView);
        ButterKnife.bind(mTracklistArtistFragmentView, rootView);
        mTracklistArtistFragmentView.initViews();
        mTrackProvider.loadAllArtists(this);
    }

    @Override
    public void onArtistsLoaded(ArrayList<ArtistModel> artists) {
        mArtistModels = artists;
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

    @Subscribe
    public void onUpdate(UpdateLibraryCompletedEvent event) {
        mTrackProvider.loadAllArtists(this);
    }

    @Subscribe
    public void onUpdateStarted(UpdateLibraryStartedEvent event) {
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}
