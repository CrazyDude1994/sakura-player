package com.crazydude.sakuraplayer.gui.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.events.TrackListUpdateEvent;
import com.crazydude.sakuraplayer.events.TracklistUpdateCompletedEvent;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.views.fragments.TracklistFragmentView;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Crazy on 25.05.2015.
 */
public class TracklistFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Inject
    TracklistFragmentView mTracklistFragmentView;

    @Inject
    LastfmApiManager mLastfmApiManager;

    public static TracklistFragment newInstance() {
        return new TracklistFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tracklist;
    }

    @Override
    protected void initViews(View rootView) {
        getActivityComponent().inject(this);
        ButterKnife.bind(mTracklistFragmentView, rootView);
        mTracklistFragmentView.initViewPager(this);
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }

    @Override
    public void onRefresh() {
        mBus.post(new TrackListUpdateEvent());
    }

    @Subscribe
    public void onTracklistUpdate(TrackListUpdateEvent event) {
        mTracklistFragmentView.setRefreshing(true);
    }

    @Subscribe
    public void onTracklistCompletedUpdate(TracklistUpdateCompletedEvent event) {
        mTracklistFragmentView.setRefreshing(false);
    }
}
