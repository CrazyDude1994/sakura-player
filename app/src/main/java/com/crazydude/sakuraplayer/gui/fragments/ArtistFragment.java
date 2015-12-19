package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.views.fragments.ArtistFragmentView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Crazy on 13.06.2015.
 */
public class ArtistFragment extends BaseFragment implements Callbacks.RecyclerViewClickListener, LoaderManager.LoaderCallbacks<ArrayList<TrackModel>> {

    private static final String KEY_ARTIST_NAME = "artist_name";
    private static final String KEY_ARTIST_ID = "artist_id";

    @Inject
    ArtistFragmentView mArtistFragmentView;

    @Inject
    MusicLibraryManager mMusicLibraryManager;

    private String mArtistName;
    private long mArtistId;

    private ArrayList<TrackModel> mTrackModels;
    private Callbacks.OnSelectedTrackListener mOnSelectedTrackListener;
    private Callbacks.OnPlayerListener mOnPlayerListener;

    public static ArtistFragment newInstance(String artistName, long artistId) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ARTIST_NAME, artistName);
        bundle.putLong(KEY_ARTIST_ID, artistId);
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
        ButterKnife.bind(this, rootView);
        mArtistFragmentView.initViews();
        mArtistName = getArguments().getString(KEY_ARTIST_NAME);
        mArtistId = getArguments().getLong(KEY_ARTIST_ID);
        mArtistFragmentView.setArtistName(mArtistName);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnSelectedTrackListener = (Callbacks.OnSelectedTrackListener) activity;
        mOnPlayerListener = (Callbacks.OnPlayerListener) activity;
    }

    @Override
    public void onClick(View view, int position) {
        mOnSelectedTrackListener.onSelectedTrack(mArtistFragmentView.getData(position));
    }

    @OnClick(R.id.fragmet_artist_add_to_playlist_floating_button)
    public void onAddToPlaylistClick() {
        PlaylistModel playlistModel = new PlaylistModel(mMusicLibraryManager.getTracksByArtistId(mArtistId), "Current");
        mOnPlayerListener.onSetPlaylist(playlistModel);
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }

    @Override
    public Loader<ArrayList<TrackModel>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<ArrayList<TrackModel>>(getContext()) {
            @Override
            public ArrayList<TrackModel> loadInBackground() {
                return mMusicLibraryManager.getTracksByArtistId(mArtistId);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<TrackModel>> loader, ArrayList<TrackModel> data) {
        mArtistFragmentView.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<TrackModel>> loader) {

    }
}
