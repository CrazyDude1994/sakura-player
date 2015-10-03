package com.crazydude.sakuraplayer.managers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.crazydude.sakuraplayer.models.AlbumModel;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by CrazyDude on 11.04.2015.
 */
public class MusicLibraryManager {

    @Inject
    Context mContext;

    public void generateDatabase() {
        new Delete().from(TrackModel.class).execute();
        new Delete().from(AlbumModel.class).execute();
        new Delete().from(ArtistModel.class).execute();

        ContentResolver contentResolver = mContext.getContentResolver();
        String[] projection = {
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.ARTIST_ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media._ID
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " = ?";
        String[] selectionArgs = {"1"};
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, selection, selectionArgs, null);
        HashMap<Long, ArtistModel> artistSet = new HashMap<>();
        HashMap<Long, AlbumModel> albumSet = new HashMap<>();
        ActiveAndroid.beginTransaction();
        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    TrackModel model = new TrackModel();
                    long artistId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
                    long albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

                    AlbumModel albumModel;
                    ArtistModel artistModel;

                    if (artistSet.containsKey(artistId)) {
                        artistModel = artistSet.get(artistId);
                    } else {
                        artistModel = getArtistById(artistId);
                        artistSet.put(artistId, artistModel);
                    }

                    if (albumSet.containsKey(albumId)) {
                        albumModel = albumSet.get(albumId);
                    } else {
                        albumModel = getAlbumById(albumId);
                        albumSet.put(albumId, albumModel);
                    }

                    if (albumModel != null) {
                        albumModel.setArtist(artistModel);
                        albumModel.save();
                    }

                    model.setAlbum(albumModel);
                    model.setArtist(artistModel);

                    model.setTrackPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                    model.setTrackId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                    model.setTrackName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                    model.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("Database", e.getMessage());
        } finally {
            ActiveAndroid.endTransaction();
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private AlbumModel getAlbumById(long id) {
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
            albumModel.save();
        }
        cursor.close();
        return albumModel;
    }

    private ArtistModel getArtistById(long id) {
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
            artistModel.save();
        }
        cursor.close();
        return artistModel;
    }
}
