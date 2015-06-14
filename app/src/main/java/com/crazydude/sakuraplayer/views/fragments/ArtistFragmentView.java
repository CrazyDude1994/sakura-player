package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistAdapter;
import com.crazydude.sakuraplayer.common.RecyclerViewTouchListener;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Crazy on 13.06.2015.
 */
@EBean
public class ArtistFragmentView extends BaseFragmentView {

    @RootContext
    Context mContext;

    @ViewById(R.id.fragmet_artist_add_to_playlist_floating_button)
    FloatingActionButton mAddToPlaylistButton;

    @ViewById(R.id.fragment_artist_recycler)
    RecyclerView mRecyclerView;

    @ViewById(R.id.fragment_artist_name)
    TextView mArtistName;

    @Bean
    TracklistAdapter mTracklistAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    @AfterViews
    void initViews() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mTracklistAdapter);
        hideToolbarShadow();
    }

    public void setOnRecyclerClickListener(Callbacks.RecyclerViewClickListener listener) {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(mContext, listener,
                mRecyclerView));
    }

    @UiThread
    public void setArtistName(String artistName) {
        mArtistName.setText(artistName);
    }

    @UiThread
    public void setData(ArrayList<TrackModel> models) {
        mTracklistAdapter.setData(models);
    }
}
