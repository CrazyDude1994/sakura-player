package com.crazydude.sakuraplayer.adapters;

import android.provider.MediaStore;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.ArtistView;
import com.crazydude.sakuraplayer.models.ArtistModel;

/**
 * Created by Crazy on 27.05.2015.
 */
public class ArtistAdapter extends BaseCursorAdapter<ArtistModel, ArtistView> {

    @Override
    public BaseViewHolder<ArtistView> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<>(new ArtistView(parent.getContext()));
    }

    @Override
    public ArtistModel getData(int position) {
        mCursor.moveToPosition(position);
        ArtistModel artistModel = new ArtistModel();
        artistModel.setArtistName(mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST)));
        return artistModel;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ArtistView> holder, int position) {
        holder.getView().setContent(getData(position));
    }
}
