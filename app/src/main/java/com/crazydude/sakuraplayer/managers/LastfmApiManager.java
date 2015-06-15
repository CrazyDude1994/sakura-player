package com.crazydude.sakuraplayer.managers;

import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.interfaces.Callbacks.OnResponseListener;
import com.crazydude.sakuraplayer.interfaces.LastfmInterface;
import com.crazydude.sakuraplayer.interfaces.Preferences_;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;
import com.crazydude.sakuraplayer.models.net.ArtistInfoResponse;
import com.crazydude.sakuraplayer.models.net.ErrorResponse;
import com.crazydude.sakuraplayer.models.net.RecommendationsResponse;
import com.crazydude.sakuraplayer.models.net.SessionResponse;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.TreeMap;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by CrazyDude on 15.03.2015.
 */
@EBean(scope = EBean.Scope.Singleton)
public class LastfmApiManager {

    @Bean
    Utils mUtils;

    @Pref
    Preferences_ mPreferences;

    LastfmInterface mLastfmInterface;

    public LastfmApiManager() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.LASTFM_API_ROOT_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mLastfmInterface = restAdapter.create(LastfmInterface.class);
    }

    @Background
    public void login(String username, String password, OnResponseListener<SessionResponse> callback) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("username", username);
        treeMap.put("password", password);
        treeMap.put("method", "auth.getMobileSession");
        String apiSig = mUtils.getSignature(treeMap);
        try {
            SessionResponse response = mLastfmInterface.login(username, password, Constants.LASTFM_API_KEY, apiSig);
            checkForErrors(response);
            if (callback != null) {
                callback.onSuccess(response);
            }
        } catch (LastfmError lastfmError) {
            if (callback != null) {
                callback.onLastfmError(lastfmError.getMessage(),
                        lastfmError.getCode());
            }
        } catch (RetrofitError error) {
            if (callback != null) {
                callback.onNetworkError(error.getMessage());
            }
        }
    }

    @Background
    public void getRecommendedArtists(int page, int limit, OnResponseListener<RecommendationsResponse> callback) {
        String session = getUserSession();
        if (!session.isEmpty()) {
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.put("page", Integer.toString(page));
            treeMap.put("limit", Integer.toString(limit));
            treeMap.put("method", "user.getRecommendedArtists");
            treeMap.put("sk", session);
            String apiSig = mUtils.getSignature(treeMap);
            try {
                RecommendationsResponse response = mLastfmInterface.getRecommendedArtists(page, limit,
                        Constants.LASTFM_API_KEY, apiSig, session);
                checkForErrors(response);
                if (callback != null) {
                    callback.onSuccess(response);
                }
            } catch (LastfmError lastfmError) {
                if (callback != null) {
                    callback.onLastfmError(lastfmError.getMessage(),
                            lastfmError.getCode());
                }
            } catch (RetrofitError error) {
                if (callback != null) {
                    callback.onNetworkError(error.getMessage());
                }
            }
        }
    }

    @Background
    public void getArtistInfo(String name, String mbid, String lang,
                       OnResponseListener<ArtistInfoResponse> callback) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("name", name);
        treeMap.put("mbid", mbid);
        treeMap.put("lang", lang);
        treeMap.put("method", "artist.getInfo");
        String apiSig = mUtils.getSignature(treeMap);
        try {
            ArtistInfoResponse response = mLastfmInterface.getArtistInfo(name, mbid, lang,
                    Constants.LASTFM_API_KEY, apiSig);
            checkForErrors(response);
            if (callback != null) {
                callback.onSuccess(response);
            }
        } catch (LastfmError lastfmError) {
            if (callback != null) {
                callback.onLastfmError(lastfmError.getMessage(),
                        lastfmError.getCode());
            }
        } catch (RetrofitError error) {
            if (callback != null) {
                callback.onNetworkError(error.getMessage());
            }
        }
    }

    @Background
    public void getNewReleases(String username, OnResponseListener<AlbumResponse> callback) {
        if (username == null) {
            username = mPreferences.lastfmUsername().get();
        }
        try {
            AlbumResponse response = mLastfmInterface.getNewReleases(username, Constants.LASTFM_API_KEY);
            checkForErrors(response);
            if (callback != null) {
                callback.onSuccess(response);
            }
        } catch (LastfmError lastfmError) {
            if (callback != null) {
                callback.onLastfmError(lastfmError.getMessage(),
                        lastfmError.getCode());
            }
        } catch (RetrofitError error) {
            if (callback != null) {
                callback.onNetworkError(error.getMessage());
            }
        }
    }

    private String getUserSession() {
        return mPreferences.lastfmToken().getOr("");
    }

    private void checkForErrors(ErrorResponse response) throws LastfmError {
        if (response.getError() != null) {
            throw new LastfmError(response.getMessage(), response.getError());
        }
    }

    public class LastfmError extends Exception {

        private Integer code;

        public LastfmError(String message, Integer code) {
            super(message);
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }
}
