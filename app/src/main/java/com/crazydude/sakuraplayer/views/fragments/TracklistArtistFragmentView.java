package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.ArtistAdapter;
import com.crazydude.sakuraplayer.common.RecyclerViewTouchListener;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.ArtistModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Crazy on 27.05.2015.
 */
@EBean
public class TracklistArtistFragmentView extends BaseFragmentView {

    @RootContext
    Context mContext;

    @ViewById(R.id.fragment_tracklist_artist_recycler)
    RecyclerView mRecyclerView;

    @Bean
    ArtistAdapter mArtistAdapter;

    @AfterViews
    void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mArtistAdapter);
    }

    public void setOnRecyclerClickListener(Callbacks.RecyclerViewClickListener listener) {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(mContext, listener,
                mRecyclerView));
    }

    @UiThread
    public void setArtistList(ArrayList<ArtistModel> models) {
        mArtistAdapter.setData(models);
    }
}
