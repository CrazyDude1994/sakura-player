package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.providers.TrackProvider;
import com.crazydude.sakuraplayer.views.fragments.ArtistFragmentView;
import com.crazydude.sakuraplayer.views.fragments.BaseFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crazy on 13.06.2015.
 */
@EFragment(R.layout.fragment_artist)
public class ArtistFragment extends BaseFragment implements Callbacks.OnTracksLoadedListener,
        Callbacks.RecyclerViewClickListener {

    @Bean
    ArtistFragmentView mArtistFragmentView;

    @Bean
    TrackProvider mTrackProvider;

    @FragmentArg
    String artistName;

    private Callbacks.OnSelectedTrackListener mOnSelectedTrackListener;
    private Callbacks.OnPlayerListener mOnPlayerListener;
    private ArrayList<TrackModel> mTrackModels;

    @AfterViews
    void initViews() {
        mTrackProvider.loadAllTracksByArtist(artistName, this);
        mArtistFragmentView.setArtistName(artistName);
    }

    @UiThread
    @Override
    public void onTrackLoaded(ArrayList<TrackModel> tracks) {
        mArtistFragmentView.setData(tracks);
        mTrackModels = tracks;
        mArtistFragmentView.setOnRecyclerClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnSelectedTrackListener = (Callbacks.OnSelectedTrackListener) activity;
        mOnPlayerListener = (Callbacks.OnPlayerListener) activity;
    }

    @Override
    public void onClick(View view, int position) {
        mOnSelectedTrackListener.onSelectedTrack(mTrackModels.get(position));
    }

    @Click(R.id.fragmet_artist_add_to_playlist_floating_button)
    void onAddToPlaylistClick() {
        PlaylistModel playlistModel = new PlaylistModel(mTrackModels, "Current");
        mOnPlayerListener.onSetPlaylist(playlistModel);
    }

    @Override
    public void onTrackLoaded(TrackModel trackModel) {

    }
}
