package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.views.fragments.ArtistFragmentView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Crazy on 13.06.2015.
 */
public class ArtistFragment extends BaseFragment implements Callbacks.OnTracksLoadedListener,
        Callbacks.RecyclerViewClickListener {

    private static final String KEY_ARTIST_NAME = "artist_name";

    @Inject
    ArtistFragmentView mArtistFragmentView;

    @Inject
    TrackProvider mTrackProvider;

    private String mArtistName;

    private Callbacks.OnSelectedTrackListener mOnSelectedTrackListener;
    private Callbacks.OnPlayerListener mOnPlayerListener;
    private ArrayList<TrackModel> mTrackModels;

    public static ArtistFragment newInstance(String artistName) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ARTIST_NAME, artistName);
        ArtistFragment artistFragment = new ArtistFragment();
        artistFragment.setArguments(bundle);
        return artistFragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_artist;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        getActivityComponent().inject(mArtistFragmentView);
        ButterKnife.bind(mArtistFragmentView, rootView);
        mArtistFragmentView.initViews();
        mArtistName = getArguments().getString(KEY_ARTIST_NAME);
        mTrackProvider.loadAllTracksByArtist(mArtistName, this);
        mArtistFragmentView.setArtistName(mArtistName);
    }

    @Override
    public void onTrackLoaded(ArrayList<TrackModel> tracks) {
        mArtistFragmentView.setData(tracks);
        mTrackModels = tracks;
        mArtistFragmentView.setOnRecyclerClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnSelectedTrackListener = (Callbacks.OnSelectedTrackListener) activity;
        mOnPlayerListener = (Callbacks.OnPlayerListener) activity;
    }

    @Override
    public void onClick(View view, int position) {
        mOnSelectedTrackListener.onSelectedTrack(mTrackModels.get(position));
    }

    @OnClick(R.id.fragmet_artist_add_to_playlist_floating_button)
    void onAddToPlaylistClick() {
        PlaylistModel playlistModel = new PlaylistModel(mTrackModels, "Current");
        mOnPlayerListener.onSetPlaylist(playlistModel);
    }

    @Override
    public void onTrackLoaded(TrackModel trackModel) {

    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}
