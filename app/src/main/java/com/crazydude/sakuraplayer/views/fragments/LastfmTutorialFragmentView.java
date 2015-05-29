package com.crazydude.sakuraplayer.views.fragments;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.andraskindler.parallaxviewpager.ParallaxViewPager;
import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.adapters.LastfmTutorialPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import java.util.HashSet;

/**
 * Created by kartavtsev.s on 28.05.2015.
 */
@EBean
public class LastfmTutorialFragmentView extends BaseFragmentView implements ViewPager.OnPageChangeListener {

    @ViewById(R.id.fragment_lastfm_tutorial_viewpager)
    ParallaxViewPager mViewPager;

    @ViewById(R.id.fragment_lastfm_tutorial_indicator)
    CirclePageIndicator mPageIndicator;

    @ViewById(R.id.fragment_lastfm_tutorial_login_button)
    Button mLoginButton;

    @ViewById(R.id.fragment_lastfm_tutorial_cancel_button)
    Button mCancelButton;

    @ViewById(R.id.fragment_lastfm_tutorial_button_container)
    View mButtonContainer;

    @RootContext
    FragmentActivity mContext;

    private LastfmTutorialPagerAdapter mPagerAdapter;
    private HashSet<ViewPager.OnPageChangeListener> mOnPageChangeListeners = new HashSet<>();;

    @AfterViews
    void initViews() {
        mPagerAdapter = new LastfmTutorialPagerAdapter(mContext.getSupportFragmentManager());

        mViewPager.setAdapter(mPagerAdapter);
        mPageIndicator.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);
        addOnPageChangeListener(mPageIndicator);
    }

    public void showLoginButton() {
        if (mButtonContainer.getVisibility() == View.GONE) {
            Display mDisplay = mContext.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            mDisplay.getSize(size);
            mButtonContainer.setTranslationY(0);
            Animator animator = ObjectAnimator.ofFloat(mButtonContainer, "translationY",
                    size.y - mButtonContainer.getY(), 0);
            animator.setDuration(500);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mButtonContainer.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();
        }
    }

    public void hideLoginButton() {
        if (mButtonContainer.getVisibility() == View.VISIBLE) {
            Display mDisplay = mContext.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            mDisplay.getSize(size);
            Animator animator = ObjectAnimator.ofFloat(mButtonContainer, "translationY", 0,
                    size.y - mButtonContainer.getY());
            animator.setDuration(500);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mButtonContainer.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();
        }
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mOnPageChangeListeners.add(listener);
    }

    public int getPagesCount() {
        if (mPagerAdapter != null) {
            return mPagerAdapter.getCount();
        }
        return 0;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (ViewPager.OnPageChangeListener listener : mOnPageChangeListeners) {
            listener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        for (ViewPager.OnPageChangeListener listener : mOnPageChangeListeners) {
            listener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        for (ViewPager.OnPageChangeListener listener : mOnPageChangeListeners) {
            listener.onPageScrollStateChanged(state);
        }
    }
}