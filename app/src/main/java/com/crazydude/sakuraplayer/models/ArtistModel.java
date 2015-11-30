package com.crazydude.sakuraplayer.models;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Crazy on 26.04.2015.
 */
@Data
@Accessors(prefix = "m")
public class ArtistModel {

    private String mArtistName;
    private List<AlbumModel> mAlbums;
}
