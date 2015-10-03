package com.crazydude.sakuraplayer.views.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.TracklistAdapter;
import com.crazydude.sakuraplayer.common.RecyclerViewTouchListener;
import com.crazydude.sakuraplayer.models.TrackModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.RecyclerViewClickListener;

/**
 * Created by Crazy on 16.05.2015.
 */
public class TracklistAllFragmentView extends BaseRefreshingView {

    @Bind(R.id.fragment_tracklist_alltracks_recycler)
    RecyclerView mRecyclerView;

    @Inject
    TracklistAdapter mTracklistAdapter;

    @Bind(R.id.fragment_tracklist_alltracks_refresher)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public TracklistAllFragmentView() {
        super();
        initViews();
    }

    void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mTracklistAdapter);
    }

    public void setOnRecyclerClickListener(RecyclerViewClickListener listener) {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(mContext, listener,
                mRecyclerView));
    }

    public TrackModel getData(int position) {
        return mTracklistAdapter.getData(position);
    }

    public void setTrackList(List<TrackModel> tracks) {
        mTracklistAdapter.setData(tracks);
    }

    @Override
    public void injectSwipeRefreshLayout() {
        super.mSwipeRefreshLayout = mSwipeRefreshLayout;
    }
}
