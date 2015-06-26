package com.crazydude.sakuraplayer.managers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.crazydude.sakuraplayer.models.AlbumModel;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.SystemService;

import java.util.ArrayList;

/**
 * Created by CrazyDude on 11.04.2015.
 */
@EBean(scope = EBean.Scope.Singleton)
public class MusicLibraryManager {

    @RootContext
    Context mContext;

    public void generateDatabase() {
        new Delete().from(ArtistModel.class).execute();
        new Delete().from(TrackModel.class).execute();
        new Delete().from(AlbumModel.class).execute();

        ContentResolver contentResolver = mContext.getContentResolver();
        String[] projection = {
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.ARTIST_ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media._ID
        };
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                TrackModel model = new TrackModel();
                long artistId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
                long albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                ArtistModel artistModel = getArtistById(artistId);
                AlbumModel albumModel = getAlbumById(albumId);
                model.setAlbum(albumModel);
                model.setArtist(artistModel);

                if (albumModel != null) {
                    albumModel.setArtist(artistModel);
                }

                model.setTrackPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                model.setTrackId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                model.setTrackName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                model.save();
            }
        }

        cursor.close();
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
            albumModel.setAlbumArt(path);
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
