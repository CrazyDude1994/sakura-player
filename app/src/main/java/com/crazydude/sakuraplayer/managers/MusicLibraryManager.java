package com.crazydude.sakuraplayer.managers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import com.crazydude.sakuraplayer.models.AlbumModel;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.venmo.cursor.CursorList;
import com.venmo.cursor.IterableCursor;
import com.venmo.cursor.IterableCursorAdapter;
import com.venmo.cursor.IterableCursorWrapper;

import java.util.ArrayList;

/**
 * Created by CrazyDude on 11.04.2015.
 */
public class MusicLibraryManager {

    private Context mContext;

    public MusicLibraryManager(Context context) {
        this.mContext = context;
    }

    public IterableCursor<TrackModel> queryAllTracks() {
        Cursor cursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        return new TrackModel.TrackModelCursor(cursor);
    }

    public IterableCursor<AlbumModel> queryAllAlbums() {
        Cursor cursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null);
        Cursor trackCursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        return new AlbumModel.AlbumModelCursor(cursor, trackCursor);
    }

    public IterableCursor<ArtistModel> queryAllArtists() {
        Cursor cursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null, null, null);
        Cursor albumCursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null);
        Cursor trackCursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        return new ArtistModel.ArtistModelCursor(cursor, albumCursor, trackCursor);
    }

    public IterableCursor<TrackModel> queryTracksByArtistId(long artistId) {
        String selection = MediaStore.Audio.Media.ARTIST_ID + " = ?";

        String[] selectionArgs = {
                String.valueOf(artistId)
        };

        Cursor cursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, selection, selectionArgs, null);
        return new TrackModel.TrackModelCursor(cursor);
    }

    public ArtistModel queryArtistById(long id) {
        Cursor cursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null, null, null);
        Cursor albumCursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null);
        Cursor trackCursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        ArtistModel.ArtistModelCursor artistModels = new ArtistModel.ArtistModelCursor(cursor, albumCursor, trackCursor);
        if (artistModels.getCount() > 0) {
            return artistModels.peek();
        } else {
            return null;
        }
    }
}
