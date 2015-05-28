package com.crazydude.sakuraplayer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.fragments.BaseFragment;
import com.crazydude.sakuraplayer.gui.fragments.TracklistAllFragment_;
import com.crazydude.sakuraplayer.gui.fragments.TracklistArtistFragment;
import com.crazydude.sakuraplayer.gui.fragments.TracklistArtistFragment_;
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
        switch (position) {
            case 0:
                return TracklistAllFragment_.builder().build();
            case 1:
                return TracklistArtistFragment_.builder().build();
            case 2:
                return TracklistAllFragment_.builder().build();
            case 3:
                return TracklistAllFragment_.builder().build();
        }
        return null;
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
