package com.crazydude.sakuraplayer.mvp.presenters;

import android.content.Context;
import android.util.Log;

import com.crazydude.sakuraplayer.mvp.events.OnLoadedTracks;
import com.crazydude.sakuraplayer.mvp.models.interfaces.TrackListModel;
import com.crazydude.sakuraplayer.mvp.presenters.interfaces.TracklistPresenter;

import de.greenrobot.event.EventBus;

/**
 * Created by Crazy on 26.04.2015.
 */
public class TracklistPresenterImpl extends BasePresenterImpl implements TracklistPresenter {
    public TracklistPresenterImpl(Context context, Object presenterView, Object model) {
        super(context, presenterView, model);
        EventBus.getDefault().register(this);
    }

    public void onEvent(OnLoadedTracks event) {
        Log.d("TRACKS", "LOADED TRACKS");
    }

    @Override
    public void loadAllTracks() {
        ((TrackListModel)mModel).loadAllTracks();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
