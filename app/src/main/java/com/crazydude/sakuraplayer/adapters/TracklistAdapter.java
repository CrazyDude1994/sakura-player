package com.crazydude.sakuraplayer.adapters;

import android.provider.MediaStore;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.TrackView;
import com.crazydude.sakuraplayer.interfaces.Callbacks.RecyclerViewClickListener;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by Crazy on 26.04.2015.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(prefix = "m")
public class TracklistAdapter extends BaseCursorAdapter<TrackModel, TrackView> {

    private RecyclerViewClickListener mOnRecyclerViewClickListener;

    @Override
    public TrackModel getData(int position) {
        mCursor.moveToPosition(position);
        TrackModel trackModel = new TrackModel();
        ArtistModel artistModel = new ArtistModel();
        trackModel.setTrackName(mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
        trackModel.setTrackId(mCursor.getLong(mCursor.getColumnIndex(MediaStore.Audio.Media._ID)));
        trackModel.setTrackPath(mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
        artistModel.setArtistName(mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
        trackModel.setArtist(artistModel);
        return trackModel;
    }

    @Override
    public BaseViewHolder<TrackView> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<>(new TrackView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<TrackView> holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.getView().setRippleCallback(rippleView -> {
            if (mOnRecyclerViewClickListener != null) {
                mOnRecyclerViewClickListener.onClick(rippleView, position);
            }
        });
    }
}
