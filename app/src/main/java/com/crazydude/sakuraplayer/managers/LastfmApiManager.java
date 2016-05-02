package com.crazydude.sakuraplayer.managers;

import android.content.Context;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.common.Utils;
import com.crazydude.sakuraplayer.events.NetworkErrorEvent;
import com.crazydude.sakuraplayer.exceptions.LastfmException;
import com.crazydude.sakuraplayer.interfaces.LastfmInterface;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;
import com.crazydude.sakuraplayer.models.net.ArtistInfoResponse;
import com.crazydude.sakuraplayer.models.net.ErrorResponse;
import com.crazydude.sakuraplayer.models.net.RecommendationsResponse;
import com.crazydude.sakuraplayer.models.net.SessionResponse;
import com.squareup.otto.Bus;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.TreeMap;

import javax.inject.Named;

import retrofit.GsonConverterFactory;
import retrofit.HttpException;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by CrazyDude on 15.03.2015.
 */
public class LastfmApiManager {

    private Utils mUtils;

    private LastfmInterface mLastfmInterface;
    private Bus mBus;
    private Context mContext;
    private PreferencesManager mPreferencesManager;

    public LastfmApiManager(Context context, Utils utils, Bus bus, PreferencesManager preferencesManager) {
        mUtils = utils;
        mBus = bus;
        mContext = context;
        mPreferencesManager = preferencesManager;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.LASTFM_API_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mLastfmInterface = retrofit.create(LastfmInterface.class);
    }

    public Observable<SessionResponse> login(String username, String password) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("username", username);
        treeMap.put("password", password);
        treeMap.put("method", "auth.getMobileSession");
        String apiSig = mUtils.getSignature(treeMap);
        return mLastfmInterface.login(username, password, Constants.LASTFM_API_KEY, apiSig)
                .compose(applyTransformers())
                .doOnNext(sessionResponse -> mPreferencesManager.saveToken(sessionResponse.getSession().getKey()));
    }

    public Observable<RecommendationsResponse> getRecommendedArtists(int page, int limit) {
        String session = getUserSession();
        if (!session.isEmpty()) {
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.put("page", Integer.toString(page));
            treeMap.put("limit", Integer.toString(limit));
            treeMap.put("method", "user.getRecommendedArtists");
            treeMap.put("sk", session);
            String apiSig = mUtils.getSignature(treeMap);

            return mLastfmInterface.getRecommendedArtists(page, limit,
                    Constants.LASTFM_API_KEY, apiSig, session).compose(applyTransformers());
        }

        return Observable.empty();
    }

    public Observable<ArtistInfoResponse> getArtistInfo(String name, String mbid, String lang) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("name", name);
        treeMap.put("mbid", mbid);
        treeMap.put("lang", lang);
        treeMap.put("method", "artist.getInfo");
        String apiSig = mUtils.getSignature(treeMap);
        return mLastfmInterface.getArtistInfo(name, mbid, lang,
                Constants.LASTFM_API_KEY, apiSig).compose(applyTransformers()).compose(applyTransformers());
    }

    public Observable<AlbumResponse> getNewReleases(String username) {
        if (username == null) {
//            username = mPreferences.lastfmUsername();
        }
        return mLastfmInterface.getNewReleases(username, Constants.LASTFM_API_KEY).compose(applyTransformers());
    }

    private String getUserSession() {
        return mPreferencesManager.getToken();
    }

    private <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private <T extends ErrorResponse> Observable.Transformer<T, T> applyErrorChecker() {
        return observable -> observable.first(t -> {
            if (t.getError() != null) {
                throw new LastfmException(t.getMessage(), t.getError());
            }
            return true;
        });
    }

    private <T extends ErrorResponse> Observable.Transformer<T, T> applyTransformers() {
        return observable -> observable
                .compose(applySchedulers())
                .compose(applyErrorChecker())
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof SocketException) {
                        mBus.post(new NetworkErrorEvent(mContext.getString(R.string.network_error),
                                mContext.getString(R.string.error_check_your_net_and_retry)));
                        return Observable.empty();
                    } else if (throwable instanceof SocketTimeoutException) {
                        mBus.post(new NetworkErrorEvent(mContext.getString(R.string.network_timeout),
                                mContext.getString(R.string.error_check_your_net_and_retry)));
                        return Observable.empty();
                    } else if (throwable instanceof LastfmException) {
                        return Observable.error(throwable);
                    } else if (throwable instanceof IOException) {
                        mBus.post(new NetworkErrorEvent(mContext.getString(R.string.network_error),
                                mContext.getString(R.string.error_check_your_net_and_retry)));
                        return Observable.empty();
                    } else if (throwable instanceof HttpException) {
                        mBus.post(new NetworkErrorEvent(mContext.getString(R.string.network_error),
                                mContext.getString(R.string.server_error)));
                        return Observable.empty();
                    } else {
                        return Observable.error(throwable);
                    }
                });
    }
}
