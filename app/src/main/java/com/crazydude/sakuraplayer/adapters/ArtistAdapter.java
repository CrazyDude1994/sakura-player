package com.crazydude.sakuraplayer.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.ArtistView;
import com.crazydude.sakuraplayer.models.ArtistModel;

import javax.inject.Inject;

/**
 * Created by Crazy on 27.05.2015.
 */
public class ArtistAdapter extends BaseAdapter<ArtistModel, ArtistView> {

    @Inject
    Context mContext;

    @Override
    public BaseViewHolder<ArtistView> onCreateViewHolder(ViewGroup parent, int viewType) {
        ArtistView artistView = new ArtistView(mContext);
        return new BaseViewHolder<>(artistView);
    }
}
