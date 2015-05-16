package com.crazydude.sakuraplayer.gui.fragments;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Constants;
import com.crazydude.sakuraplayer.managers.LastfmApiManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by CrazyDude on 14.03.2015.
 */
@EFragment(R.layout.fragment_lastfm_login)
public class LastfmLoginFragment extends BaseFragment {

    @ViewById(R.id.fragment_lastfm_login_webview)
    WebView mWebView;

    @Bean
    LastfmApiManager mApiManager;

    private String mToken;

    @AfterViews
    void initViews() {

        final class LastfmWebClient extends WebViewClient {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.startsWith(Constants.LASTFM_GRANTED_URL)) {
                    //showProgressBar();
                    getSession();
                }
            }
        }

        mWebView.setWebViewClient(new LastfmWebClient());
        //showProgressBar();
        getToken();
    }

    @Background
    void getToken() {
/*        try {
            TreeMap<String, String> params = new TreeMap<String, String>();
            params.put("method", "auth.getToken");
            String signature = Utils.getSignature(params);
            LastfmTokenResponse response = mApiManager.getToken(signature);
            if (!response.token.isEmpty()) {
                mToken = response.token;
                requestAuthorization(response.token);
            }
        } catch (RestClientException e) {
            hideProgressBar();
        }*/
    }

    @Background
    void getSession() {
/*        try {
            TreeMap<String, String> params = new TreeMap<String, String>();
            params.put("method", "auth.getSession");
            params.put("token", mToken);
            String signature = Utils.getSignature(params);
            LastfmSessionResponse response = mApiManager.getSession(signature, mToken);
            if (response != null) {
                if (!response.session.key.isEmpty()) {
                    EventBus.getDefault().post(new SessionEvent(response.session.key));
                }
            }
        } catch (RestClientException e) {
            hideProgressBar();
        }*/
    }

    @UiThread
    void requestAuthorization(String token) {
       // hideProgressBar();
        mWebView.loadUrl(Constants.LASTFM_LOGIN_URL + token);
        mWebView.setVisibility(View.VISIBLE);
    }
}