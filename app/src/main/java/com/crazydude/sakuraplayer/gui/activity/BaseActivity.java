package com.crazydude.sakuraplayer.gui.activity;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.crazydude.sakuraplayer.gui.fragments.BaseFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Crazy on 24.05.2015.
 */
public class BaseActivity extends FragmentActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void switchFragment(BaseFragment fragment, boolean isAddToBackstack, int containerId) {
        String fragmentTag = fragment.getClass().getSimpleName();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment, fragmentTag);
        if (isAddToBackstack) {
            fragmentTransaction.addToBackStack(fragmentTag);
        }
        fragmentTransaction.commit();
    }

    public void addFragment(BaseFragment fragment, boolean isAddToBackstack, int containerId) {
        String fragmentTag = fragment.getClass().getSimpleName();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerId, fragment, fragmentTag);
        if (isAddToBackstack) {
            fragmentTransaction.addToBackStack(fragmentTag);
        }
        fragmentTransaction.commit();
    }
}
