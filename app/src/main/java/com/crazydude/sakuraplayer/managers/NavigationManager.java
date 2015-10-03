package com.crazydude.sakuraplayer.managers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.common.Constants.FragmentsEnum;
import com.crazydude.sakuraplayer.features.Features;
import com.crazydude.sakuraplayer.interfaces.FeatureProvider;
import com.crazydude.sakuraplayer.interfaces.SwitchableFragment;
import com.squareup.otto.Bus;

import java.lang.ref.WeakReference;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Crazy on 25.09.2015.
 */
@Data
@Accessors(prefix = "m")
public class NavigationManager {

    private static final String CURRENT_FRAGMENT_KEY = "CURRENT_FRAGMENT";
    private static final String CURRENT_FRAGMENT_COUNTER_KEY = "CURRENT_FRAGMENT_COUNTER";

    private int mId = 0;

    private String mCurrentFragment = "";
    private FragmentManager mFragmentManager;
    private Bus mBus;
    private WeakReference<AppCompatActivity> mActivity;

    public NavigationManager(AppCompatActivity activity, FragmentManager fragmentManager, Bus bus) {
        this.mActivity = new WeakReference<>(activity);
        this.mFragmentManager = fragmentManager;
        this.mBus = bus;
    }

    public void switchFragment(FragmentsEnum fragment, boolean addToBackStack, Object... arguments) {
        extendedSwitchFragment(fragment, addToBackStack, false, arguments);
    }

    public void extendedSwitchFragment(FragmentsEnum fragment, boolean addToBackStack,
                                       boolean addInsteadOfReplace, Object... arguments) {
        Fragment newFragment = null;

/*        switch (fragment) {
            case ArtistFragment:
                newFragment = ArtistFragment_.builder().artistName((String) arguments[0]).build();
                break;
            case LastfmArtistFragment:
                newFragment = LastfmArtistFragment_.builder()
                        .artistName((String) arguments[0])
                        .mbid((String) arguments[1])
                        .build();
                break;
            case LastfmTutorialFragment:
                newFragment = LastfmTutorialFragment_.builder().build();
                break;
            case LastReleasesFragment:
                newFragment = LastReleasesFragment_.builder().build();
                break;
            case PlayerFragment:
                newFragment = PlayerFragment_.builder().build();
                break;
            case RecommendsFragment:
                newFragment = RecommendsFragment_.builder().build();
                break;
            case TracklistFragment:
                newFragment = TracklistFragment_.builder().build();
                break;
        }*/

        Features features = ((SwitchableFragment) newFragment).requestFeatures(new Features.FeaturesBuilder());
        ((FeatureProvider) mActivity.get()).provideFeatures(features);

        String fragmentTag = newFragment.getClass().getName() + mId;
        mId++;

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentTag);
        }

        if (!addInsteadOfReplace) {
            fragmentTransaction = fragmentTransaction.replace(R.id.activity_home_placeholder, newFragment, fragmentTag);
            if (!addToBackStack) {
                getFragmentManager().popBackStack();
                fragmentTransaction.addToBackStack(fragmentTag);
            }
        } else {
            fragmentTransaction = fragmentTransaction.add(R.id.activity_home_placeholder, newFragment, fragmentTag);
        }
        mCurrentFragment = fragmentTag;

        fragmentTransaction.commit();
    }

    public void storeCurrentFragment(Bundle bundle) {
        bundle.putString(CURRENT_FRAGMENT_KEY, mCurrentFragment);
        bundle.putInt(CURRENT_FRAGMENT_COUNTER_KEY, mId);
    }

    public void restoreCurrentFragment(Bundle bundle) {
        if (bundle != null) {
            mId = bundle.getInt(CURRENT_FRAGMENT_COUNTER_KEY);
            mCurrentFragment = bundle.getString(CURRENT_FRAGMENT_KEY);
            SwitchableFragment fragment = (SwitchableFragment) getFragmentManager().findFragmentByTag(mCurrentFragment);

            if (fragment != null) {
                Features features = fragment.requestFeatures(new Features.FeaturesBuilder());
                ((FeatureProvider) mActivity.get()).provideFeatures(features);
            }
        }
    }

    public void handleBackButtonPress() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStackImmediate();
            int count = getFragmentManager().getBackStackEntryCount();
            String lastId = getFragmentManager().getBackStackEntryAt(count - 1).getName();
            Features features = ((SwitchableFragment) getFragmentManager().findFragmentByTag(lastId))
                    .requestFeatures(new Features.FeaturesBuilder());

            ((FeatureProvider) mActivity.get()).provideFeatures(features);
            mCurrentFragment = lastId;
        } else {
            mActivity.get().finish();
        }
    }
}
