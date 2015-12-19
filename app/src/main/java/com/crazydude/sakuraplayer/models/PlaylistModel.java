package com.crazydude.sakuraplayer.models;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by kartavtsev.s on 11.06.2015.
 */
@Data
@Accessors(prefix = "m")
@AllArgsConstructor
public class PlaylistModel {

    private ArrayList<TrackModel> mTracks;
    private String mName;
}
