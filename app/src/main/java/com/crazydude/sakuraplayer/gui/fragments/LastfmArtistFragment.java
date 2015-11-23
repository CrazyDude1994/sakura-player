package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.models.net.ArtistInfoResponse;
import com.crazydude.sakuraplayer.models.net.ArtistResponse;
import com.crazydude.sakuraplayer.views.fragments.LastfmArtistFragmentView;
import com.crazydude.sakuraplayer.views.fragments.RecommendsFragmentView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by kartavtsev.s on 08.06.2015.
 */
public class LastfmArtistFragment extends BaseFragment implements Callbacks.RecyclerViewClickListener {

    @Inject
    LastfmArtistFragmentView mLastfmArtistFragmentView;

    @Inject
    RecommendsFragmentView mRecommendsFragmentView;

    String artistName;

    String mbid;

    @Inject
    LastfmApiManager mLastfmApiManager;

    private Callbacks.OnSelectedLastfmArtistListener mOnSelectedLastfmArtistListener;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_lastfm_artist;
    }

    @Override
    protected void initViews(View rootView) {
        mRecommendsFragmentView.setOnRecyclerClickListener(this);
        loadArtistInfo(artistName, mbid);
    }

    private void loadArtistInfo(String artistName, String mbid) {
        mLastfmApiManager.getArtistInfo(artistName, mbid, "ru");
    }

    void loadArtistImage(String url) {
        Picasso.with(getActivity())
                .load(url)
                .into(mLastfmArtistFragmentView);
    }

/*    @Override
    public void onSuccess(ArtistInfoResponse response) {
        mLastfmArtistFragmentView.showContent();
        mLastfmArtistFragmentView.setData(response);
        mRecommendsFragmentView.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecommendsFragmentView.setColumnCount(1);
        mRecommendsFragmentView.setData(response.getArtist().getSimilar().getArtist());
        loadArtistImage(response.getArtist().getImages().get(3).getUrl()); // achtung!
    }*/

    @Override
    public void onClick(View view, int position) {
        ArtistResponse data = mRecommendsFragmentView.getData(position);
        mOnSelectedLastfmArtistListener.onSelecteLastfmArtist(data.getName(), data.getMbid());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnSelectedLastfmArtistListener = (Callbacks.OnSelectedLastfmArtistListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + Callbacks.OnSelectedLastfmArtistListener.class.getSimpleName());
        }
    }

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}
