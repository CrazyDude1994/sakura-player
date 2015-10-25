package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.features.ToolbarFeature;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialFragmentView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnLastfmTutorialCompletedListener;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
public class LastfmTutorialFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @Inject
    LastfmTutorialFragmentView mLastfmTutorialFragmentView;

    private OnLastfmTutorialCompletedListener mLastfmTutorialCompletedListener;

    public static LastfmTutorialFragment newInstance() {
        return new LastfmTutorialFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lastfm_tutorial, container, false);
        ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        ButterKnife.bind(mLastfmTutorialFragmentView, view);
        mLastfmTutorialFragmentView.initViews();
        mLastfmTutorialFragmentView.addOnPageChangeListener(this);
        return view;
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
                    + " must implement " + OnLastfmTutorialCompletedListener.class.getSimpleName());
        }
    }

    @OnClick({R.id.fragment_lastfm_tutorial_login_button, R.id.fragment_lastfm_tutorial_cancel_button})
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

    @Override
    public Features requestFeatures(Features.FeaturesBuilder builder) {
        return builder.addFeature(ToolbarFeature.builder().isBackButton(true).build()).build();
    }
}
