package com.crazydude.sakuraplayer.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.RecyclerViewClickListener;

/**
 * Created by Crazy on 18.05.2015.
 */
public class RecyclerViewTouchListener extends GestureDetector.SimpleOnGestureListener implements RecyclerView.OnItemTouchListener {
    private Context mContext;
    private RecyclerViewClickListener mRecycleViewClicksHandler;
    private RecyclerView mRecyclerView;
    private GestureDetector mGestureDetector;

    public RecyclerViewTouchListener(Context context, RecyclerViewClickListener recycleViewClicksHandler, RecyclerView recyclerView) {
        mContext = context;
        mRecycleViewClicksHandler = recycleViewClicksHandler;
        mRecyclerView = recyclerView;
        mGestureDetector = new GestureDetector(mContext, this);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        View view = getChildView(e);
        if (view != null) {
            mRecycleViewClicksHandler.onClick(view, mRecyclerView.getChildPosition(view));
        }
        return super.onSingleTapUp(e);
    }

    private View getChildView(MotionEvent e) {
        View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
        return child;
    }
}
