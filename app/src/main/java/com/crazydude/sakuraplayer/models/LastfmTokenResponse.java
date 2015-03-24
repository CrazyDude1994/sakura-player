package com.crazydude.sakuraplayer.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by CrazyDude on 14.03.2015.
 */
public class LastfmTokenResponse extends LastfmError {

    @JsonProperty("token")
    public String token;
}
