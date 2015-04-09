package com.crazydude.sakuraplayer.interfaces;

import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.models.LastfmSessionResponse;
import com.crazydude.sakuraplayer.models.LastfmTokenResponse;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;

/**
 * Created by CrazyDude on 14.03.2015.
 */
public interface LastfmInterface {

    LastfmTokenResponse getToken(String signature);
    LastfmSessionResponse getSession(String signature, String token);
}
