package com.crazydude.sakuraplayer.models;

import android.database.Cursor;
import android.provider.MediaStore;

import com.venmo.cursor.CursorList;
import com.venmo.cursor.IterableCursorWrapper;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Crazy on 26.04.2015.
 */
@Data
@Accessors(prefix = "m")
public class ArtistModel {

    private long mId;
    private String mArtistName;
    private String mArtistArt;
    private List<AlbumModel> mAlbums;

    public static class ArtistModelCursor extends IterableCursorWrapper<ArtistModel> {

        private Cursor mAlbumCursor;
        private Cursor mTrackCursor;

        public ArtistModelCursor(Cursor cursor, Cursor albumCursor, Cursor trackCursor) {
            super(cursor);
            this.mAlbumCursor = albumCursor;
            this.mTrackCursor = trackCursor;
        }

        @Override
        public ArtistModel peek() {
            ArtistModel artistModel = new ArtistModel();

            artistModel.setId(getLong(MediaStore.Audio.Artists._ID, 0));
            artistModel.setArtistName(getString(MediaStore.Audio.Artists.ARTIST, ""));
            artistModel.setAlbums(new CursorList<>(new AlbumModel.AlbumModelCursor(mAlbumCursor, mTrackCursor)));
            artistModel.setArtistArt("");

            return artistModel;
        }
    }

}
