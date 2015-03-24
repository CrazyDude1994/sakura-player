package com.crazydude.sakuraplayer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by CrazyDude on 15.03.2015.
 */
public class LastfmSessionResponse extends LastfmError {

    @JsonProperty("session")
    public Session session = new Session();

    public class Session {

        @JsonProperty("name")
        public String name = "";

        @JsonProperty("key")
        public String key = "";

        @JsonProperty("subscriber")
        public String subscriber = "";
    }
}
