package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;

import com.crazydude.sakuraplayer.R;

/**
 * Created by CrazyDude on 14.03.2015.
 */
public class LastfmLoginFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle("Dialog");
        builder.setMessage("Lorem ipsum dolor ....");
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Cancel", null);
        return builder;
    }
}