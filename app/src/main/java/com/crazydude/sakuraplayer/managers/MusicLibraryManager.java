package com.crazydude.sakuraplayer.managers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.crazydude.sakuraplayer.models.AlbumModel;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.venmo.cursor.IterableCursor;

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
        return new TrackModel.TrackModelCursor(cursor, this);
    }

    public IterableCursor<AlbumModel> queryAllAlbums() {
        Cursor cursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null);
        return new AlbumModel.AlbumModelCursor(cursor, this);
    }

    public IterableCursor<ArtistModel> queryAllArtists() {
        Cursor cursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null, null, null);
        return new ArtistModel.ArtistModelCursor(cursor, this);
    }

    public IterableCursor<TrackModel> queryTracksByArtistId(long artistId) {
        String selection = MediaStore.Audio.Media.ARTIST_ID + " = ?";

        String[] selectionArgs = {
                String.valueOf(artistId)
        };

        Cursor cursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, selection, selectionArgs, null);
        return new TrackModel.TrackModelCursor(cursor, this);
    }

    public IterableCursor<AlbumModel> queryAlbumsByArtistId(long artistId) {
        Uri albumsUri = MediaStore.Audio.Artists.Albums.getContentUri("external", artistId);

        return new AlbumModel.AlbumModelCursor(mContext.getContentResolver()
                .query(albumsUri, null, null, null, null), this);
    }

    public AlbumModel queryAlbumById(long albumId) {
        String selection = MediaStore.Audio.Albums._ID + " = ?";

        String[] selectionArgs = {
                String.valueOf(albumId)
        };

        Cursor cursor = mContext.getContentResolver()
                .query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, selection, selectionArgs, null);
        AlbumModel.AlbumModelCursor albumModels = new AlbumModel.AlbumModelCursor(cursor, this);
        if (albumModels.getCount() > 0) {
            return albumModels.peek();
        } else {
            return null;
        }
    }
}
