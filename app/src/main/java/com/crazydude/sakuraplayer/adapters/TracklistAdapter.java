package com.crazydude.sakuraplayer.adapters;

import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.TrackView;
import com.crazydude.sakuraplayer.models.TrackModel;

/**
 * Created by Crazy on 26.04.2015.
 */
public class TracklistAdapter extends BaseAdapter<TrackModel, TrackView> {

    @Override
    public BaseViewHolder<TrackView> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<>(new TrackView(parent.getContext()));
    }
}
