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

    @Override
    public BaseViewHolder<ArtistView> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<>(new ArtistView(parent.getContext()));
    }
}
