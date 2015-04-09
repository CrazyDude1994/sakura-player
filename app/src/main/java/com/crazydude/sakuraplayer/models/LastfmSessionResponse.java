package com.crazydude.sakuraplayer.models;


import java.util.List;

/**
 * Created by CrazyDude on 15.03.2015.
 */
public class LastfmSessionResponse {

    public Session session = new Session();

    public class Session {

        public String name = "";

        public String key = "";

        public String subscriber = "";
    }
}
