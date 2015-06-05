package com.crazydude.sakuraplayer.models.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kartavtsev.s on 05.06.2015.
 */
public class RecommendationsResponse extends ErrorResponse {

    private Recommendations recommendations;

    public Recommendations getRecommendations() {
        return recommendations;
    }

    public class Recommendations {

        private List<Artist> artist;

        public List<Artist> getArtists() {
            return artist;
        }

        public class Artist {

            private String name;
            private String url;

            public List<Image> getImages() {
                return image;
            }

            private List<Image> image;

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
    }
}
