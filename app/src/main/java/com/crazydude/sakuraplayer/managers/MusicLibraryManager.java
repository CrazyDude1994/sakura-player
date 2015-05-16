package com.crazydude.sakuraplayer.managers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

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

        return getTracks(projection, selection, selectionArgs, null);
    }

    public HashSet<TrackModel> getArtistList() {
        String[] projection = {
                MediaStore.Audio.Media.ARTIST
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " = ?";
        String[] selectionArgs = {
                "1",
        };

        HashSet<TrackModel> result = new HashSet<>(getTracks(projection, selection, selectionArgs,
                MediaStore.Audio.Media.ARTIST));

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

        return getTracks(projection, selection, selectionArgs, null);
    }

    private ArrayList<TrackModel> getTracks(String[] projection, String selection,
                                        String[] selectionArgs, String columnName) {
        ArrayList<TrackModel> result = new ArrayList<>();
        ContentResolver contentResolver = mContext.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, selection, selectionArgs, null);
        if (columnName == null) {
            columnName = MediaStore.Audio.Media.DATA;
        }
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String data = cursor.getString(cursor.getColumnIndex(columnName));
                TrackModel model = new TrackModel();
                model.setTrackPath(data);
                result.add(model);
            }
        }

        cursor.close();
        return result;
    }
}
