package com.crazydude.sakuraplayer.managers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.crazydude.sakuraplayer.models.AlbumModel;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;

import java.util.ArrayList;

/**
 * Created by CrazyDude on 11.04.2015.
 */
public class MusicLibraryManager {

    private Context mContext;

    public MusicLibraryManager(Context context) {
        this.mContext = context;
    }

    public TrackModel loadTrackModel(Cursor cursor) {
        TrackModel trackModel = new TrackModel();
        ArtistModel artistModel = new ArtistModel();
        trackModel.setTrackName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
        trackModel.setTrackId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
        trackModel.setTrackPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
        artistModel.setArtistName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
        trackModel.setArtist(artistModel);
        return trackModel;
    }

    public ArrayList<TrackModel> getTracksByArtistId(long artistId) {
        ArrayList<TrackModel> trackModels = new ArrayList<>();

        String selection = MediaStore.Audio.Media.ARTIST_ID + "=?";

        String[] selectionArgs = {String.valueOf(artistId)};

        Cursor cursor = mContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, selection, selectionArgs, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                trackModels.add(loadTrackModel(cursor));
            }

            cursor.close();
        }

        return trackModels;
    }

    public AlbumModel getAlbumById(long id) {
        AlbumModel albumModel = null;

        ContentResolver contentResolver = mContext.getContentResolver();
        String[] projection = {
                MediaStore.Audio.AlbumColumns.ALBUM,
                MediaStore.Audio.AlbumColumns.ALBUM_ART,
        };
        String selection = MediaStore.Audio.Albums._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        Cursor cursor = contentResolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, projection,
                selection, selectionArgs, null);
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM));
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ART));
            albumModel = new AlbumModel();
            albumModel.setAlbumArtPath(path);
            albumModel.setName(name);
        }
        cursor.close();
        return albumModel;
    }

    public ArtistModel getArtistById(long id) {
        ArtistModel artistModel = null;

        ContentResolver contentResolver = mContext.getContentResolver();
        String[] projection = {
                MediaStore.Audio.ArtistColumns.ARTIST
        };
        String selection = MediaStore.Audio.Artists._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        Cursor cursor = contentResolver.query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, projection,
                selection, selectionArgs, null);
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
            artistModel = new ArtistModel();
            artistModel.setArtistName(name);
        }
        cursor.close();
        return artistModel;
    }
}
