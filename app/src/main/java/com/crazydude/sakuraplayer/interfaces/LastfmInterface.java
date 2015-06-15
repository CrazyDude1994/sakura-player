package com.crazydude.sakuraplayer.interfaces;

import com.crazydude.sakuraplayer.models.net.AlbumResponse;
import com.crazydude.sakuraplayer.models.net.ArtistInfoResponse;
import com.crazydude.sakuraplayer.models.net.RecommendationsResponse;
import com.crazydude.sakuraplayer.models.net.SessionResponse;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by CrazyDude on 14.03.2015.
 */
public interface LastfmInterface {

    @FormUrlEncoded
    @POST("/?method=auth.getMobileSession&format=json")
    SessionResponse login(@Field("username") String username,
                          @Field("password") String password,
                          @Field("api_key") String apiKey,
                          @Field("api_sig") String apiSig);

    @FormUrlEncoded
    @POST("/?method=user.getRecommendedArtists&format=json")
    RecommendationsResponse getRecommendedArtists(@Field("page") int page,
                                                  @Field("limit") int limit,
                                                  @Field("api_key") String apiKey,
                                                  @Field("api_sig") String apiSig,
                                                  @Field("sk") String session);

    @FormUrlEncoded
    @POST("/?method=artist.getInfo&format=json")
    ArtistInfoResponse getArtistInfo(@Field("artist") String name,
                                     @Field("mbid") String mbid,
                                     @Field("lang") String lang,
                                     @Field("api_key") String apiKey,
                                     @Field("api_sig") String apiSig);

    @FormUrlEncoded
    @POST("/?method=user.getNewReleases&format=json")
    AlbumResponse getNewReleases(@Field("user") String username,
                                 @Field("api_key") String apiKey);
}
