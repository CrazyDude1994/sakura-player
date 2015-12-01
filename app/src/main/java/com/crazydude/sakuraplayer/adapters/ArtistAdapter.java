package com.crazydude.sakuraplayer.adapters;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.ArtistView;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.models.ArtistModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.*;

/**
 * Created by Crazy on 27.05.2015.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(prefix = "m")
public class ArtistAdapter extends BaseCursorAdapter<ArtistModel, ArtistView> {

    private Context mContext;
    private RecyclerViewClickListener mOnRecyclerViewClickListener;

    public ArtistAdapter(Context context) {
        mContext = context;
    }

    @Override
    public BaseViewHolder<ArtistView> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<>(new ArtistView(parent.getContext()));
    }

    @Override
    public ArtistModel getData(int position) {
        mCursor.moveToPosition(position);
        ArtistModel artistModel = new ArtistModel();
        artistModel.setArtistName(mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST)));
        Cursor albumCursor =
                mContext
                        .getContentResolver()
                        .query(MediaStore.Audio.Artists.Albums.getContentUri("external", mCursor.getLong(mCursor.getColumnIndex(MediaStore.Audio.Artists._ID))), null, null, null, null);
        String albumArt = "";
        if (albumCursor != null) {
            while (albumCursor.moveToNext()) {
                if (albumCursor.getString(albumCursor.getColumnIndex(MediaStore.Audio.Artists.Albums.ALBUM_ART)) != null) {
                    albumArt = albumCursor.getString(albumCursor.getColumnIndex(MediaStore.Audio.Artists.Albums.ALBUM_ART));
                }
                if (albumArt != null && !albumArt.isEmpty()) {
                    break;
                }
            }

            albumCursor.close();
        }

        artistModel.setArtistArt(albumArt);
        return artistModel;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ArtistView> holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.getView().setRippleCallback(rippleView -> {
            if (mOnRecyclerViewClickListener != null) {
                mOnRecyclerViewClickListener.onClick(rippleView, position);
            }
        });
    }
}
