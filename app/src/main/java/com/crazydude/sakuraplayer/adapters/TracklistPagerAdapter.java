package com.crazydude.sakuraplayer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.fragments.TracklistAllFragment_;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * Created by Crazy on 18.05.2015.
 */
public class TracklistPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    static final int[] ICONS = new int[]{R.drawable.ic_audiotrack_selector, R.drawable.ic_piano_selector,
            R.drawable.ic_album_selector, R.drawable.ic_playlists_selector};

    private Context mContext;

    public TracklistPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TracklistAllFragment_.builder().build();
    }


    @Override
    public int getIconResId(int index) {
        return ICONS[index];
    }

    @Override
    public int getCount() {
        return ICONS.length;
    }

}
