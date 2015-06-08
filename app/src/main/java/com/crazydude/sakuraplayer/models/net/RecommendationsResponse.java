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

        private List<ArtistResponse> artist;

        public List<ArtistResponse> getArtists() {
            return artist;
        }
    }
}
