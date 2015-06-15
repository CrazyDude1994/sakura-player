package com.crazydude.sakuraplayer.models.net;

import java.util.List;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
public class AlbumResponse extends ErrorResponse {

    private Albums albums;

    public Albums getAlbums() {
        return albums;
    }

    public class Albums {

        private List<Album> album;

        public List<Album> getAlbum() {
            return album;
        }
    }

    public class Album {

        private String name;
        private String mbid;
        private String url;
        private ArtistResponse artist;

        public List<ArtistResponse.Image> getImage() {
            return image;
        }
        private List<ArtistResponse.Image> image;

        public String getName() {
            return name;
        }

        public String getMbid() {
            return mbid;
        }

        public String getUrl() {
            return url;
        }

        public ArtistResponse getArtist() {
            return artist;
        }

    }
}
