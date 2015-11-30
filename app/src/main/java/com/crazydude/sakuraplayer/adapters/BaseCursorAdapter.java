package com.crazydude.sakuraplayer.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.interfaces.DataView;

import java.util.List;

import javax.inject.Inject;

import static android.support.v7.widget.RecyclerView.Adapter;

/**
 * Created by Crazy on 16.05.2015.
 */
abstract class BaseCursorAdapter<ModelType, ViewType extends View & DataView<ModelType>> extends
        Adapter<BaseViewHolder<ViewType>> {

    protected Cursor mCursor;

    public BaseCursorAdapter(Cursor cursor) {
        mCursor = cursor;
    }

    abstract public BaseViewHolder<ViewType> onCreateViewHolder(ViewGroup parent, int viewType);
    abstract public void onBindViewHolder(BaseViewHolder<ViewType> holder, int position);

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}