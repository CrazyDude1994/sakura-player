package com.crazydude.sakuraplayer.adapters;

import android.provider.MediaStore;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.TrackView;
import com.crazydude.sakuraplayer.models.TrackModel;

/**
 * Created by Crazy on 26.04.2015.
 */
public class TracklistAdapter extends BaseCursorAdapter<TrackModel, TrackView> {

    @Override
    public TrackModel getData(int position) {
        mCursor.moveToPosition(position);
        TrackModel trackModel = new TrackModel();
        trackModel.setTrackName(mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
        return trackModel;
    }

    @Override
    public BaseViewHolder<TrackView> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<>(new TrackView(parent.getContext()));
    }
}
