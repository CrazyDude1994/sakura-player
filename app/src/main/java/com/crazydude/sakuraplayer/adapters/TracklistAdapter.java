package com.crazydude.sakuraplayer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazydude.sakuraplayer.gui.views.TrackView;
import com.crazydude.sakuraplayer.gui.views.TrackView_;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

/**
 * Created by Crazy on 26.04.2015.
 */
@EBean
public class TracklistAdapter extends BaseAdapter<TrackModel> {

    @RootContext
    Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TrackView trackView = TrackView_.build(mContext);
        return new ViewHolder(trackView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TrackView trackView = ((ViewHolder)holder).getView();
        trackView.setContent(getData(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TrackView mTrackView;

        public ViewHolder(TrackView trackView) {
            super(trackView);
            this.mTrackView = trackView;
        }

        public TrackView getView() {
            return mTrackView;
        }
    }
}
