//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.crazydude.sakuraplayer.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.crazydude.sakuraplayer.R.layout;
import com.crazydude.sakuraplayer.views.fragments.LastfmTutorialTextFragmentView_;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LastfmTutorialTextFragment_
    extends com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialTextFragment
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private View contentView_;
    public final static String TUTORIAL_TEXT_ARG = "tutorialText";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    @Override
    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.fragment_lastfm_tutorial_text, container, false);
        }
        return contentView_;
    }

    @Override
    public void onDestroyView() {
        contentView_ = null;
        super.onDestroyView();
    }

    private void init_(Bundle savedInstanceState) {
        injectFragmentArguments_();
        mLastfmTutorialTextFragmentView = LastfmTutorialTextFragmentView_.getInstance_(getActivity());
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static LastfmTutorialTextFragment_.FragmentBuilder_ builder() {
        return new LastfmTutorialTextFragment_.FragmentBuilder_();
    }

    private void injectFragmentArguments_() {
        Bundle args_ = getArguments();
        if (args_!= null) {
            if (args_.containsKey(TUTORIAL_TEXT_ARG)) {
                tutorialText = args_.getString(TUTORIAL_TEXT_ARG);
            }
        }
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        initViews();
    }

    public static class FragmentBuilder_
        extends FragmentBuilder<LastfmTutorialTextFragment_.FragmentBuilder_, com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialTextFragment>
    {


        @Override
        public com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialTextFragment build() {
            LastfmTutorialTextFragment_ fragment_ = new LastfmTutorialTextFragment_();
            fragment_.setArguments(args);
            return fragment_;
        }

        public LastfmTutorialTextFragment_.FragmentBuilder_ tutorialText(String tutorialText) {
            args.putString(TUTORIAL_TEXT_ARG, tutorialText);
            return this;
        }

    }

}
