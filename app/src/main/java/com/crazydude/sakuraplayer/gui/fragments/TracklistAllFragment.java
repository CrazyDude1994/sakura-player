package com.crazydude.sakuraplayer.gui.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.mvp.models.TrackListModelImpl;
import com.crazydude.sakuraplayer.mvp.presenters.TracklistPresenterImpl;
import com.crazydude.sakuraplayer.mvp.presenters.interfaces.TracklistPresenter;
import com.crazydude.sakuraplayer.mvp.views.interfaces.TracklistAllView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by CrazyDude on 13.04.2015.
 */
@EFragment(R.layout.fragment_tracklist_alltracks)
public class TracklistAllFragment extends BaseFragment implements TracklistAllView {

    private TracklistPresenter mTracklistAllPresenter;

    @Bean
    TrackListModelImpl trackListModel;

    @ViewById(R.id.fragment_tracklist_alltracks_recycler)
    RecyclerView mRecyclerView;


    @AfterInject
    void init() {
        mTracklistAllPresenter = new TracklistPresenterImpl(this.getActivity(), this, trackListModel);
    }

    @AfterViews
    void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onPause() {
        mTracklistAllPresenter.onPause();
    }

    @Override
    public void onResume() {
        mTracklistAllPresenter.onResume();
    }

    @Override
    public void setTrackList(List<TrackModel> tracks) {

    }
}
