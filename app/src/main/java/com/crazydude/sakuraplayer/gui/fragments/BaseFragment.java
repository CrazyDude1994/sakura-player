package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Fragment;
import com.crazydude.sakuraplayer.gui.activity.HomeActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

/**
 * Created by CrazyDude on 15.03.2015.
 */
@EFragment
public class BaseFragment extends Fragment {

    @UiThread
    public void hideProgressBar() {
        try {
            ((HomeActivity) getActivity()).hideProgressBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @UiThread
    public void showProgressBar() {
        try {
            ((HomeActivity) getActivity()).showProgressBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
