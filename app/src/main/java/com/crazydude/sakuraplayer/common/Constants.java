package com.crazydude.sakuraplayer.common;


/**
 * Created by CrazyDude on 14.03.2015.
 */
public class Constants {

    public final static int SPLASH_DURATION = 3000;

    public final static String LASTFM_API_ROOT_URL = "https://ws.audioscrobbler.com/2.0/";
    public final static String LASTFM_API_KEY = "30f5f9d26d668aa917f0b76bd3fa5773";
    public static final String LASTFM_API_SECRET = "a4bab0f23cdfe0c0aa3ac0a60b5fef86";

    public enum FragmentsEnum {
        ArtistFragment(0),
        LastfmArtistFragment(1),
        LastfmTutorialFragment(2),
        LastReleasesFragment(3),
        PlayerFragment(4),
        RecommendsFragment(5),
        TracklistFragment(6);

        private int dialogId;

        FragmentsEnum(int dialogId) {
            this.dialogId = dialogId;
        }

        public static FragmentsEnum getById(int id) {
            for (FragmentsEnum fragmentsEnum : FragmentsEnum.values()) {
                if (fragmentsEnum.getId() == id) {
                    return fragmentsEnum;
                }
            }
            return null;
        }

        public int getId() {
            return dialogId;
        }
    }
}
