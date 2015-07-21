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
public class TracklistAdapter extends BaseAdapter<TrackModel, TrackView> {

    @RootContext
    Context mContext;

    @Override
    public BaseViewHolder<TrackView> onCreateViewHolder(ViewGroup parent, int viewType) {
        TrackView trackView = TrackView_.build(mContext);
        return new BaseViewHolder<>(trackView);
    }
}
