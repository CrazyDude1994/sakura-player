package com.crazydude.sakuraplayer.views.fragments;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.NewReleasesAdapter;
import com.crazydude.sakuraplayer.common.RecyclerViewTouchListener;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
@EBean
public class LastReleasesFragmentView extends BaseFragmentView {

    @RootContext
    AppCompatActivity mActivity;

    @ViewById(R.id.fragment_last_releases_recycler)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayout;
    private NewReleasesAdapter mAdapter;

    @AfterViews
    void initViews() {
        mAdapter = new NewReleasesAdapter();
        mLinearLayout = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLinearLayout);
        mRecyclerView.setAdapter(mAdapter);
    }

    @UiThread
    public void setData(AlbumResponse data) {
        mAdapter.setData(data.getAlbums().getAlbum());
    }

}
