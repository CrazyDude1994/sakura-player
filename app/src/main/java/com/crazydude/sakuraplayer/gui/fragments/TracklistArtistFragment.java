package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.events.RequestUpdateLibraryEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryCompletedEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryStartedEvent;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.views.fragments.TracklistArtistFragmentView;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Crazy on 27.05.2015.
 */
public class TracklistArtistFragment extends BaseFragment implements Callbacks.RecyclerViewClickListener, SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor> {

    @Inject
    TracklistArtistFragmentView mTracklistArtistFragmentView;

    @Inject
    MusicLibraryManager mMusicLibraryManager;

    @Inject
    TrackProvider mTrackProvider;

    private Callbacks.OnSelectedArtistListener mOnSelectedArtistListener;

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
        mTracklistArtistFragmentView.setOnRecyclerClickListener(this);
        mTracklistArtistFragmentView.setOnRefreshListener(this);
        getParentFragment().getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public void onClick(View view, int position) {
        mOnSelectedArtistListener.onSelectedArtist(mTracklistArtistFragmentView.getData(position));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnSelectedArtistListener = (Callbacks.OnSelectedArtistListener) activity;
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }

    @Override
    public void onRefresh() {
        mBus.post(new RequestUpdateLibraryEvent());
    }

    @Subscribe
    public void onLibraryUpdated(UpdateLibraryCompletedEvent event) {
        mTracklistArtistFragmentView.setRefreshing(false);
        getParentFragment().getLoaderManager().restartLoader(1, null, this);
    }

    @Subscribe
    public void onLibraryUpdating(UpdateLibraryStartedEvent event) {
        mTracklistArtistFragmentView.setRefreshing(true);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return getActivityComponent().getArtistCursorLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mTracklistArtistFragmentView.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mTracklistArtistFragmentView.setData(null);
    }
}
