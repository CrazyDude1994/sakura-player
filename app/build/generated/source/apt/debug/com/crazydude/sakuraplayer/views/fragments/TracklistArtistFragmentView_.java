//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.crazydude.sakuraplayer.views.fragments;

import java.util.ArrayList;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import com.crazydude.sakuraplayer.R.id;
import com.crazydude.sakuraplayer.adapters.ArtistAdapter_;
import com.crazydude.sakuraplayer.models.ArtistModel;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class TracklistArtistFragmentView_
    extends TracklistArtistFragmentView
    implements OnViewChangedListener
{

    private Context context_;
    private Handler handler_ = new Handler(Looper.getMainLooper());

    private TracklistArtistFragmentView_(Context context) {
        context_ = context;
        init_();
    }

    public static TracklistArtistFragmentView_ getInstance_(Context context) {
        return new TracklistArtistFragmentView_(context);
    }

    private void init_() {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        mContext = context_;
        mArtistAdapter = ArtistAdapter_.getInstance_(context_);
    }

    public void rebind(Context context) {
        context_ = context;
        init_();
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        mRecyclerView = ((RecyclerView) hasViews.findViewById(id.fragment_tracklist_artist_recycler));
        initViews();
    }

    @Override
    public void setArtistList(final ArrayList<ArtistModel> models) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                TracklistArtistFragmentView_.super.setArtistList(models);
            }

        }
        );
    }

}
