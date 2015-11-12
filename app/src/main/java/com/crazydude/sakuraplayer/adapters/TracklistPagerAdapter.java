package com.crazydude.sakuraplayer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.fragments.TracklistAllFragment;
import com.crazydude.sakuraplayer.gui.fragments.TracklistArtistFragment;

/**
 * Created by Crazy on 18.05.2015.
 */
public class TracklistPagerAdapter extends FragmentPagerAdapter {

    static final String[] TITLES = new String[]{"Треки", "Исполнители", "Альбомы", "Плейлисты"};

    FragmentManager mFragmentManager;

    public TracklistPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TracklistAllFragment();
            case 1:
                return new TracklistArtistFragment();
            case 2:
                return new TracklistAllFragment();
            case 3:
                return new TracklistAllFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

}
