//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.crazydude.sakuraplayer.managers;

import android.content.Context;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class MusicLibraryManager_
    extends MusicLibraryManager
{

    private Context context_;
    private static MusicLibraryManager_ instance_;

    private MusicLibraryManager_(Context context) {
        context_ = context;
    }

    public static MusicLibraryManager_ getInstance_(Context context) {
        if (instance_ == null) {
            OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(null);
            instance_ = new MusicLibraryManager_(context.getApplicationContext());
            instance_.init_();
            OnViewChangedNotifier.replaceNotifier(previousNotifier);
        }
        return instance_;
    }

    private void init_() {
        mContext = context_;
    }

}