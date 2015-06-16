package com.crazydude.sakuraplayer.managers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import com.crazydude.sakuraplayer.models.AlbumModel;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

/**
 * Created by CrazyDude on 11.04.2015.
 */
@EBean(scope = EBean.Scope.Singleton)
public class MusicLibraryManager {

    @RootContext
    Context mContext;

    public ArrayList<TrackModel> getAllTracks() {
        String[] projection = {
                MediaStore.Audio.Media.DATA
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " = ?";
        String[] selectionArgs = {
                "1"
        };

        return getTracks(selection, selectionArgs);
    }

    public ArrayList<TrackModel> getTrackById(long id) {
        String[] projection = {
                MediaStore.Audio.Media.DATA
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " = ? AND "
                + MediaStore.Audio.Media._ID + " = ?";
        String[] selectionArgs = {
                "1",
                Long.toString(id)
        };

        return getTracks(selection, selectionArgs);
    }

    public ArrayList<ArtistModel> getArtistList() {
        String selection = MediaStore.Audio.Media.IS_MUSIC + " = ?) GROUP BY (" + MediaStore.Audio.Media.ARTIST;
        String[] selectionArgs = {
                "1",
        };

        ArrayList<ArtistModel> result = new ArrayList<>(getArtists(selection, selectionArgs));

        return result;
    }

    public ArrayList<TrackModel> getTracksByArtist(String artistName) {
        String[] projection = {
                MediaStore.Audio.Media.DATA
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " = ?";
        selection += " AND " + MediaStore.Audio.Media.ARTIST + " = ?";
        String[] selectionArgs = {
                "1",
                artistName
        };

        return getTracks(selection, selectionArgs);
    }

    private ArrayList<TrackModel> getTracks(String selection, String[] selectionArgs) {
        ArrayList<TrackModel> result = new ArrayList<>();
        ContentResolver contentResolver = mContext.getContentResolver();
        String[] projection = {
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media._ID
        };
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, selection, selectionArgs, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                TrackModel model = new TrackModel();
                ArtistModel artistModel = new ArtistModel();
                artistModel.setArtistName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                model.setTrackPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                model.setArtist(artistModel);
                model.setTrackId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                model.setTrackName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                AlbumModel albumModel = getAlbumById(id);
                model.setAlbum(albumModel);
                result.add(model);
            }
        }

        cursor.close();
        return result;
    }

    private AlbumModel getAlbumById(long id) {
        AlbumModel albumModel = null;
        ContentResolver contentResolver = mContext.getContentResolver();
        String[] projection = {
                MediaStore.Audio.AlbumColumns.ALBUM,
                MediaStore.Audio.AlbumColumns.ALBUM_ART,
        };
        String selection = "_ID = ?";
        String[] selectionArgs = {Long.toString(id)};
        Cursor cursor = contentResolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, projection,
                selection, selectionArgs, null);
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM));
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ART));
            albumModel = new AlbumModel();
            albumModel.setAlbumArt(path);
            albumModel.setName(name);
        }
        cursor.close();
        return albumModel;
    }

    private ArrayList<ArtistModel> getArtists(String selection, String[] selectionArgs) {
        ArrayList<ArtistModel> result = new ArrayList<>();
        ContentResolver contentResolver = mContext.getContentResolver();
        String[] projection = {
                MediaStore.Audio.Media.ARTIST,
        };
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, selection, selectionArgs, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ArtistModel artistModel = new ArtistModel();
                artistModel.setArtistName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                result.add(artistModel);
            }
        }

        cursor.close();
        return result;
    }
}
