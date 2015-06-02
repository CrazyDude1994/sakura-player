package com.crazydude.sakuraplayer.gui.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.Callbacks;

/**
 * Created by CrazyDude on 14.03.2015.
 */
public class LastfmLoginFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private Callbacks.OnLastfmLoginListener mOnLastfmLoginListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.login_to_lastfm);
        builder.setPositiveButton(R.string.login, this);
        builder.setNegativeButton(R.string.cancel, this);
        builder.setNeutralButton(R.string.register, this);
        builder.setView(R.layout.fragment_lastfm_login);
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnLastfmLoginListener = (Callbacks.OnLastfmLoginListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement " +
                    Callbacks.OnLastfmLoginListener.class.getSimpleName());
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                TextView mLoginText = (TextView)getDialog()
                        .findViewById(R.id.fragment_lastfm_login_login_text);
                TextView mPasswordText = (TextView)getDialog()
                        .findViewById(R.id.fragment_lastfm_login_password_text);
                mOnLastfmLoginListener.onLastfmLogin(mLoginText.getText().toString(),
                        mPasswordText.getText().toString());
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                mOnLastfmLoginListener.onLastfmRegister();
                break;
        }
    }
}