package com.crazydude.sakuraplayer.adapters;

import android.database.Cursor;
import android.provider.MediaStore;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.ArtistView;
import com.crazydude.sakuraplayer.models.AlbumModel;
import com.crazydude.sakuraplayer.models.ArtistModel;

import java.util.ArrayList;

/**
 * Created by Crazy on 27.05.2015.
 */
public class ArtistAdapter extends BaseCursorAdapter<ArtistModel, ArtistView> {

    public ArtistAdapter(Cursor cursor) {
        super(cursor);
    }

    @Override
    public BaseViewHolder<ArtistView> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<>(new ArtistView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ArtistView> holder, int position) {
        mCursor.moveToPosition(position);

        long artistId = mCursor.getLong(mCursor.getColumnIndex(MediaStore.Audio.Artists._ID));
        Cursor albumsCursor = holder.getView().getContext().getContentResolver()
                .query(MediaStore.Audio.Artists.Albums.getContentUri(null, artistId), null, null, null, null);
        ArrayList<AlbumModel> albums = new ArrayList<>();
        ArtistModel artistModel = new ArtistModel();
/*        while (albumsCursor.moveToNext()) {
            AlbumModel albumModel = new AlbumModel();
*//*            albumModel.setAlbumArt(albumsCursor.getString(albumsCursor.getColumnIndex(MediaStore.Audio.Artists.Albums.ALBUM_ART)));
            albumModel.setArtist(artistModel);*//*
            albums.add(albumModel);
        }*/

        artistModel.setArtistName(mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST)));
//        artistModel.setAlbums(null);
        holder.getView().setContent(artistModel);
    }
}
