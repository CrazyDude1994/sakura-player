package com.crazydude.sakuraplayer.managers;

import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.interfaces.LastfmInterface;
import com.crazydude.sakuraplayer.models.net.ErrorResponse;
import com.crazydude.sakuraplayer.models.net.SessionResponse;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

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

    LastfmInterface mLastfmInterface;

    public LastfmApiManager() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.LASTFM_API_ROOT_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mLastfmInterface = restAdapter.create(LastfmInterface.class);
    }

    @Background
    public void login(String username, String password, Callbacks.OnResponseListener callback) {
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
