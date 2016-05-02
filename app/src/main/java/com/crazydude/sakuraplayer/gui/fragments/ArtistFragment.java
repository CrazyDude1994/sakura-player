package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistAdapter;
import com.crazydude.sakuraplayer.common.RecyclerViewTouchListener;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.venmo.cursor.CursorList;
import com.venmo.cursor.IterableCursor;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Crazy on 13.06.2015.
 */
public class ArtistFragment extends BaseFragment implements Callbacks.RecyclerViewClickListener {

    private static final String KEY_ARTIST_NAME = "artist_name";
    private static final String KEY_ARTIST_ID = "artist_id";

    @Inject
    MusicLibraryManager mMusicLibraryManager;

    @Bind(R.id.fragmet_artist_add_to_playlist_floating_button)
    FloatingActionButton mAddToPlaylistButton;

    @Bind(R.id.fragment_artist_recycler)
    RecyclerView mRecyclerView;

    @Bind(R.id.fragment_artist_name)
    TextView mArtistNameText;

    @Bind(R.id.fragment_artist_coordinator)
    CoordinatorLayout mCoordinatorLayout;

    @Inject
    TracklistAdapter mTracklistAdapter;

    @Inject
    @Named("Activity")
    Context mContext;

    private String mArtistName;
    private long mArtistId;

    private Callbacks.OnSelectedTrackListener mOnSelectedTrackListener;
    private Callbacks.OnPlayerListener mOnPlayerListener;
    private IterableCursor<TrackModel> mTrackModels;
    private LinearLayoutManager mLinearLayoutManager;

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
        mArtistName = getArguments().getString(KEY_ARTIST_NAME);
        mArtistId = getArguments().getLong(KEY_ARTIST_ID);
        mArtistNameText.setText(mArtistName);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mTracklistAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(mContext, this,
                mRecyclerView));
        loadData();
    }

    private void loadData() {
        mTrackModels = mMusicLibraryManager.queryTracksByArtistId(mArtistId);
        setData(mTrackModels);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnSelectedTrackListener = (Callbacks.OnSelectedTrackListener) activity;
        mOnPlayerListener = (Callbacks.OnPlayerListener) activity;
    }

    @Override
    public void onClick(View view, int position) {
        mOnSelectedTrackListener.onSelectedTrack(getData(position));
    }

    @OnClick(R.id.fragmet_artist_add_to_playlist_floating_button)
    public void onAddToPlaylistClick() {
        CursorList<TrackModel> trackModels = new CursorList<>(mTrackModels);
        PlaylistModel playlistModel = new PlaylistModel(trackModels, "Current");
        mOnPlayerListener.onSetPlaylist(playlistModel);
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }

    public void setData(IterableCursor<TrackModel> cursor) {
        mTracklistAdapter.setCursor(cursor);
    }

    public TrackModel getData(int position) {
        return mTracklistAdapter.getData(position);
    }
}
