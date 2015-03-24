package com.crazydude.sakuraplayer.interfaces;

import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.models.LastfmSessionResponse;
import com.crazydude.sakuraplayer.models.LastfmTokenResponse;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by CrazyDude on 14.03.2015.
 */
@Rest(converters = MappingJackson2HttpMessageConverter.class, rootUrl = Constants.LASTFM_API_ROOT_URL)
public interface LastfmInterface {

    @Get("?method=auth.getToken&format=json&api_sig={signature}&api_key=" + Constants.LASTFM_API_KEY)
    LastfmTokenResponse getToken(String signature);

    @Get("?method=auth.getSession&format=json&api_sig={signature}&token={token}&api_key=" + Constants.LASTFM_API_KEY)
    LastfmSessionResponse getSession(String signature, String token);
}
