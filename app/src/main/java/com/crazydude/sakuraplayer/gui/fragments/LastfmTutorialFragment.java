package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.activity.HomeActivity;
import com.crazydude.sakuraplayer.interfaces.Callbacks;
import com.crazydude.sakuraplayer.interfaces.Preferences_;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialFragmentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.sharedpreferences.Pref;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.*;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
@EFragment(R.layout.fragment_lastfm_tutorial)
public class LastfmTutorialFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @Bean
    LastfmTutorialFragmentView mLastfmTutorialFragmentView;

    private OnLastfmTutorialCompletedListener mLastfmTutorialCompletedListener;

    @AfterViews
    void initViews() {
        mLastfmTutorialFragmentView.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == mLastfmTutorialFragmentView.getPagesCount() - 1) {
            mLastfmTutorialFragmentView.showLoginButton();
        } else {
            mLastfmTutorialFragmentView.hideLoginButton();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mLastfmTutorialCompletedListener = (OnLastfmTutorialCompletedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + mLastfmTutorialCompletedListener.getClass().getSimpleName());
        }
    }

    @Click({R.id.fragment_lastfm_tutorial_login_button, R.id.fragment_lastfm_tutorial_cancel_button})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_lastfm_tutorial_login_button:
                mLastfmTutorialCompletedListener.onTutorialCompleted(true);
                break;
            case R.id.fragment_lastfm_tutorial_cancel_button:
                mLastfmTutorialCompletedListener.onTutorialCompleted(false);
                break;
        }
    }
}
