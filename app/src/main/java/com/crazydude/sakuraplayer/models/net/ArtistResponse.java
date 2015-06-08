package com.crazydude.sakuraplayer.models.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kartavtsev.s on 08.06.2015.
 */
public class ArtistResponse {

    private String name;
    private String url;
    private String mbid;
    private List<Image> image;

    public String getMbid() {
        return mbid;
    }

    public List<Image> getImages() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public class Image {

        @SerializedName("#text")
        private String url;
        private String size;

        public String getUrl() {
            return url;
        }

        public String getSize() {
            return size;
        }
    }
}
