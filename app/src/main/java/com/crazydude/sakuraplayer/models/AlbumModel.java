package com.crazydude.sakuraplayer.models;

import android.database.Cursor;
import android.provider.MediaStore;

import com.crazydude.sakuraplayer.managers.MusicLibraryManager;
import com.venmo.cursor.CursorList;
import com.venmo.cursor.IterableCursorWrapper;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by kartavtsev.s on 16.06.2015.
 */
@Data
@Accessors(prefix = "m")
public class AlbumModel {

    private String mArtistName;
    private String mName;
    private String mAlbumArtPath;
    private List<TrackModel> mTracks;

    public static class AlbumModelCursor extends IterableCursorWrapper<AlbumModel> {

        private MusicLibraryManager mMusicLibraryManager;

        public AlbumModelCursor(Cursor cursor, MusicLibraryManager musicLibraryManager) {
            super(cursor);
            mMusicLibraryManager = musicLibraryManager;
        }

        @Override
        public AlbumModel peek() {
            AlbumModel albumModel = new AlbumModel();
            albumModel.setAlbumArtPath(getString(MediaStore.Audio.Albums.ALBUM_ART, "") == null ? "" : getString(MediaStore.Audio.Albums.ALBUM_ART, ""));
            albumModel.setArtistName(getString(MediaStore.Audio.Albums.ARTIST, ""));
            albumModel.setName(getString(MediaStore.Audio.Albums.ALBUM, ""));
            albumModel.setTracks(new CursorList<>(mMusicLibraryManager.queryTracksByArtistId(getLong(getColumnIndex(MediaStore.Audio.Albums.ARTIST)))));
            return albumModel;
        }
    }
}
