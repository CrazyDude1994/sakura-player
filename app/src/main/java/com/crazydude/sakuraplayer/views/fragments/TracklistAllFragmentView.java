package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistAdapter;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by Crazy on 16.05.2015.
 */
@EBean
public class TracklistAllFragmentView extends BaseFragmentView {

    @ViewById(R.id.fragment_tracklist_alltracks_recycler)
    RecyclerView mRecyclerView;

    @RootContext
    Context mContext;

    @Bean
    TracklistAdapter mTracklistAdapter;

    @AfterViews
    void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
    }

    public void setTrackList(List<TrackModel> tracks) {
        mTracklistAdapter.setData(tracks);
    }
}
