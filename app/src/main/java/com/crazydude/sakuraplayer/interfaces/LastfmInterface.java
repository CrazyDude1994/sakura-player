package com.crazydude.sakuraplayer.interfaces;

import com.crazydude.sakuraplayer.models.net.RecommendationsResponse;
import com.crazydude.sakuraplayer.models.net.SessionResponse;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
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
                                                  @Field("api_sig") String api_sig,
                                                  @Field("sk") String session);

}
