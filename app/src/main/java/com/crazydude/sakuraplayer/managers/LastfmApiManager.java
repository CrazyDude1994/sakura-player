package com.crazydude.sakuraplayer.managers;

import com.crazydude.sakuraplayer.interfaces.LastfmInterface;
import com.crazydude.sakuraplayer.models.LastfmSessionResponse;
import com.crazydude.sakuraplayer.models.LastfmTokenResponse;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.RestClientException;

/**
 * Created by CrazyDude on 15.03.2015.
 */
@EBean(scope = EBean.Scope.Singleton)
public class LastfmApiManager {

    @RestService
    LastfmInterface lastfmApi;

    public LastfmTokenResponse getToken(String signature) {
        try {
            LastfmTokenResponse response = lastfmApi.getToken(signature);
            return response;
        } catch (RestClientException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public LastfmSessionResponse getSession(String signature, String token) {
        try {
            LastfmSessionResponse response = lastfmApi.getSession(signature, token);
            return response;
        } catch (RestClientException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
