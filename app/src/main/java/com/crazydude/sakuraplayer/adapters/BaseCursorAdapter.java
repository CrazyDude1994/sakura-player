package com.crazydude.sakuraplayer.adapters;

import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.interfaces.DataView;

import static android.support.v7.widget.RecyclerView.Adapter;

/**
 * Created by Crazy on 16.05.2015.
 */
abstract class BaseCursorAdapter<ModelType, ViewType extends View & DataView<ModelType>> extends
        Adapter<BaseViewHolder<ViewType>> {

    protected Cursor mCursor;

    public void setCursor(Cursor cursor) {
        if (this.mCursor != null) {
            mCursor.close();
        }
        this.mCursor = cursor;
        notifyDataSetChanged();
    }

    abstract public BaseViewHolder<ViewType> onCreateViewHolder(ViewGroup parent, int viewType);
    abstract public ModelType getData(int position);

    @Override
    public void onBindViewHolder(BaseViewHolder<ViewType> holder, int position) {
        holder.getView().setContent(getData(position));
    }

    @Override
    public int getItemCount() {
        return (mCursor == null) ? 0 : mCursor.getCount();
    }
}
