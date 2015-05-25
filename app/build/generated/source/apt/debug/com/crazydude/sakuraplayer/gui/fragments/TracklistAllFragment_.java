//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.crazydude.sakuraplayer.gui.fragments;

import java.util.List;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.crazydude.sakuraplayer.R.layout;
import com.crazydude.sakuraplayer.managers.MusicLibraryManager_;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.crazydude.sakuraplayer.providers.TrackProvider_;
import com.crazydude.sakuraplayer.views.fragments.TracklistAllFragmentView_;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class TracklistAllFragment_
    extends com.crazydude.sakuraplayer.gui.fragments.TracklistAllFragment
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private View contentView_;
    private Handler handler_ = new Handler(Looper.getMainLooper());

    @java.lang.Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    @java.lang.Override
    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

    @java.lang.Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.fragment_tracklist_alltracks, container, false);
        }
        return contentView_;
    }

    @java.lang.Override
    public void onDestroyView() {
        contentView_ = null;
        super.onDestroyView();
    }

    private void init_(Bundle savedInstanceState) {
        mMusicLibraryManager = MusicLibraryManager_.getInstance_(getActivity());
        mTrackProvider = TrackProvider_.getInstance_(getActivity());
        mTracklistAllFragmentView = TracklistAllFragmentView_.getInstance_(getActivity());
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @java.lang.Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static TracklistAllFragment_.FragmentBuilder_ builder() {
        return new TracklistAllFragment_.FragmentBuilder_();
    }

    @java.lang.Override
    public void onViewChanged(HasViews hasViews) {
        initViews();
    }

    @java.lang.Override
    public void onTrackLoaded(final List<TrackModel> tracks) {
        handler_.post(new Runnable() {


            @java.lang.Override
            public void run() {
                TracklistAllFragment_.super.onTrackLoaded(tracks);
            }

        }
        );
    }

    public static class FragmentBuilder_
        extends FragmentBuilder<TracklistAllFragment_.FragmentBuilder_, com.crazydude.sakuraplayer.gui.fragments.TracklistAllFragment>
    {


        @java.lang.Override
        public com.crazydude.sakuraplayer.gui.fragments.TracklistAllFragment build() {
            TracklistAllFragment_ fragment_ = new TracklistAllFragment_();
            fragment_.setArguments(args);
            return fragment_;
        }

    }

}
