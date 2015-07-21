package com.crazydude.sakuraplayer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.ArtistView;
import com.crazydude.sakuraplayer.gui.views.ArtistView_;
import com.crazydude.sakuraplayer.models.ArtistModel;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by Crazy on 27.05.2015.
 */
@EBean
public class ArtistAdapter extends BaseAdapter<ArtistModel, ArtistView> {

    @RootContext
    Context mContext;

    @Override
    public BaseViewHolder<ArtistView> onCreateViewHolder(ViewGroup parent, int viewType) {
        ArtistView artistView = ArtistView_.build(mContext);
        return new BaseViewHolder<>(artistView);
    }
}
