package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistAdapter;
import com.crazydude.sakuraplayer.common.RecyclerViewTouchListener;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.TrackModel;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.Bind;
import io.codetail.animation.RevealAnimator;
import io.codetail.animation.SupportAnimator;
import io.codetail.widget.RevealFrameLayout;

/**
 * Created by Crazy on 13.06.2015.
 */
public class ArtistFragmentView {

    @Bind(R.id.fragmet_artist_add_to_playlist_floating_button)
    FloatingActionButton mAddToPlaylistButton;

    @Bind(R.id.fragment_artist_recycler)
    RecyclerView mRecyclerView;

    @Bind(R.id.fragment_artist_name)
    TextView mArtistName;

    @Bind(R.id.fragment_artist_coordinator)
    CoordinatorLayout mCoordinatorLayout;

    @Inject
    TracklistAdapter mTracklistAdapter;

    @Inject
    @Named("Activity")
    Context mContext;

    private LinearLayoutManager mLinearLayoutManager;

    public void initViews() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mTracklistAdapter);
    }

    public void setOnRecyclerClickListener(Callbacks.RecyclerViewClickListener listener) {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(mContext, listener,
                mRecyclerView));
    }

    public void setArtistName(String artistName) {
        mArtistName.setText(artistName);
    }

    public void setData(ArrayList<TrackModel> cursor) {
//        mTracklistAdapter.setCursor(cursor);
    }

    public TrackModel getData(int position) {
        return mTracklistAdapter.getData(position);
    }
}
