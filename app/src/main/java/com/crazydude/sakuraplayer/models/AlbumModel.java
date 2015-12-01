package com.crazydude.sakuraplayer.models;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by kartavtsev.s on 16.06.2015.
 */
@Data
@Accessors(prefix = "m")
public class AlbumModel {

    private ArtistModel mArtist;
    private String mName;
    private String mAlbumArtPath;
    private List<TrackModel> mTracks;
}
