package com.crazydude.sakuraplayer.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by CrazyDude on 14.03.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastfmError {
    @JsonProperty("error")
    public int error = 0;

    @JsonProperty("message")
    public String message = "";
}
