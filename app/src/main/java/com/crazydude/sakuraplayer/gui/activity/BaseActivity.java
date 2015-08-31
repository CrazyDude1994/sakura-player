package com.crazydude.sakuraplayer.gui.activity;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.crazydude.sakuraplayer.gui.fragments.BaseFragment;
import com.crazydude.sakuraplayer.providers.BusProvider;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Crazy on 24.05.2015.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        BusProvider.getInstance().unregister(this);
        super.onPause();
    }
}
