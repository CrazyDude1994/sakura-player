package com.crazydude.sakuraplayer.models.net;

/**
 * Created by kartavtsev.s on 02.06.2015.
 */
public class SessionResponse extends ErrorResponse {

    private Session session;

    public Session getSession() {
        return session;
    }

    public class Session {

        private String name;
        private String key;
        private String subscriber;

        public String getName() {
            return name;
        }

        public String getKey() {
            return key;
        }

        public String getSubscriber() {
            return subscriber;
        }
    }
}
