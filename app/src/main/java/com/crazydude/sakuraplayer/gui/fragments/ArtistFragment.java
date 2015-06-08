package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;
import com.crazydude.sakuraplayer.models.net.ArtistInfoResponse;
import com.crazydude.sakuraplayer.models.net.ArtistResponse;
import com.crazydude.sakuraplayer.views.fragments.ArtistFragmentView;
import com.crazydude.sakuraplayer.views.fragments.RecommendsFragmentView;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;

/**
 * Created by kartavtsev.s on 08.06.2015.
 */
@EFragment(R.layout.fragment_artist)
public class ArtistFragment extends BaseFragment implements
        Callbacks.OnResponseListener<ArtistInfoResponse>, Callbacks.RecyclerViewClickListener {

    @Bean
    ArtistFragmentView mArtistFragmentView;

    @Bean
    RecommendsFragmentView mRecommendsFragmentView;

    @FragmentArg
    String artistName;

    @FragmentArg
    String mbid;

    @Bean
    LastfmApiManager mLastfmApiManager;

    private Callbacks.OnSelectedArtistListener mOnSelectedArtistListener;

    @AfterViews
    void initViews() {
        mArtistFragmentView.showProgressBar();
        mRecommendsFragmentView.setOnRecyclerClickListener(this);
        loadArtistInfo(artistName, mbid);
    }

    private void loadArtistInfo(String artistName, String mbid) {
        mLastfmApiManager.getArtistInfo(artistName, mbid, "ru", this);
    }

    @UiThread
    void loadArtistImage(String url) {
        Picasso.with(getActivity())
                .load(url)
                .into(mArtistFragmentView);
    }

    @Override
    public void onSuccess(ArtistInfoResponse response) {
        mArtistFragmentView.hideProgressBar();
        mArtistFragmentView.showContent();
        mArtistFragmentView.setData(response);
        mRecommendsFragmentView.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecommendsFragmentView.setColumnCount(1);
        mRecommendsFragmentView.setData(response.getArtist().getSimilar().getArtist());
        loadArtistImage(response.getArtist().getImages().get(3).getUrl()); // achtung!
    }

    @Override
    public void onLastfmError(String message, Integer code) {
        mArtistFragmentView.hideProgressBar();
    }

    @Override
    public void onNetworkError(String message) {
        mArtistFragmentView.hideProgressBar();
    }

    @Override
    public void onClick(View view, int position) {
        ArtistResponse data = mRecommendsFragmentView.getData(position);
        mOnSelectedArtistListener.onSelectedArtist(data.getName(), data.getMbid());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnSelectedArtistListener = (Callbacks.OnSelectedArtistListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + Callbacks.OnSelectedArtistListener.class.getSimpleName());
        }
    }
}
