package com.crazydude.sakuraplayer.managers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.crazydude.sakuraplayer.models.ArtistModel;
import com.crazydude.sakuraplayer.models.TrackModel;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.HashSet;

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

    public HashSet<TrackModel> getArtistList() {
        String[] projection = {
                MediaStore.Audio.Media.ARTIST
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " = ?";
        String[] selectionArgs = {
                "1",
        };

        HashSet<TrackModel> result = new HashSet<>(getTracks(selection, selectionArgs));

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
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA
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
                model.setTrackName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                model.setAlbumName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                result.add(model);
            }
        }

        cursor.close();
        return result;
    }
}
