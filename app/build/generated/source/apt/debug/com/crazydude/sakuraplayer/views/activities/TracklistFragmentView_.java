//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.crazydude.sakuraplayer.views.activities;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.crazydude.sakuraplayer.R.id;
import com.viewpagerindicator.IconPageIndicator;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class TracklistFragmentView_
    extends TracklistFragmentView
    implements OnViewChangedListener
{

    private Context context_;

    private TracklistFragmentView_(Context context) {
        context_ = context;
        init_();
    }

    public static TracklistFragmentView_ getInstance_(Context context) {
        return new TracklistFragmentView_(context);
    }

    private void init_() {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        if (context_ instanceof FragmentActivity) {
            mContext = ((FragmentActivity) context_);
        } else {
            Log.w("TracklistFragmentView_", (("Due to Context class "+ context_.getClass().getSimpleName())+", the @RootContext FragmentActivity won't be populated"));
        }
    }

    public void rebind(Context context) {
        context_ = context;
        init_();
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        mIconPageIndicator = ((IconPageIndicator) hasViews.findViewById(id.fragment_tracklist_viewpager_titles));
        mViewPager = ((ViewPager) hasViews.findViewById(id.fragment_tracklist_viewpager));
        initViews();
    }

}
