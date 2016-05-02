package com.crazydude.sakuraplayer.models;

import android.database.Cursor;
import android.provider.MediaStore;

import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.venmo.cursor.IterableCursorWrapper;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Crazy on 26.04.2015.
 */
@Data
@Accessors(prefix = "m")
public class TrackModel {

    private String mArtistName;
    private String mTrackName;
    private String mTrackPath;
    private String mAlbumName;
    private long mTrackId;
    private String mAlbumArtPath;

    public static class TrackModelCursor extends IterableCursorWrapper<TrackModel> {

        private MusicLibraryManager mMusicLibraryManager;

        public TrackModelCursor(Cursor cursor, MusicLibraryManager musicLibraryManager) {
            super(cursor);
            mMusicLibraryManager = musicLibraryManager;
        }

        @Override
        public TrackModel peek() {
            TrackModel trackModel = new TrackModel();
            trackModel.setTrackName(getString(getColumnIndex(MediaStore.Audio.Media.TITLE)));
            trackModel.setTrackId(getLong(getColumnIndex(MediaStore.Audio.Media._ID)));
            trackModel.setTrackPath(getString(getColumnIndex(MediaStore.Audio.Media.DATA)));
            trackModel.setArtistName(getString(MediaStore.Audio.Media.ARTIST, ""));
            trackModel.setAlbumName(getString(MediaStore.Audio.Media.ALBUM, ""));
            AlbumModel albumModel = mMusicLibraryManager.queryAlbumById(getLong(getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
            trackModel.setAlbumArtPath(albumModel == null ? "" : albumModel.getAlbumArtPath());
            return trackModel;
        }
    }
}
