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
 * Created by Crazy on 26.04.2015.
 */
@Data
@Accessors(prefix = "m")
public class ArtistModel {

    private long mId;
    private String mArtistName;
    private String mArtistArt;
    private List<AlbumModel> mAlbums;

    public static class ArtistModelCursor extends IterableCursorWrapper<ArtistModel> {

        private MusicLibraryManager mMusicLibraryManager;

        public ArtistModelCursor(Cursor cursor, MusicLibraryManager musicLibraryManager) {
            super(cursor);
            mMusicLibraryManager = musicLibraryManager;
        }

        @Override
        public ArtistModel peek() {
            ArtistModel artistModel = new ArtistModel();
            artistModel.setId(getLong(MediaStore.Audio.Artists._ID, 0));
            artistModel.setArtistName(getString(MediaStore.Audio.Artists.ARTIST, ""));

            artistModel.setAlbums(new CursorList<>(mMusicLibraryManager.queryAlbumsByArtistId(artistModel.getId())));
            for (AlbumModel albumModel : artistModel.getAlbums()) {
                if (albumModel.getAlbumArtPath() != null && !albumModel.getAlbumArtPath().isEmpty()) {
                    artistModel.setArtistArt(albumModel.getAlbumArtPath());
                    break;
                }
            }

            if (artistModel.getArtistArt() == null) {
                artistModel.setArtistArt("");
            }

            return artistModel;
        }
    }

}
