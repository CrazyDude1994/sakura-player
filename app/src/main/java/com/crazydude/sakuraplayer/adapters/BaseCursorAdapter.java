package com.crazydude.sakuraplayer.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.interfaces.DataView;
import com.venmo.cursor.IterableCursor;

import static android.support.v7.widget.RecyclerView.Adapter;

/**
 * Created by Crazy on 16.05.2015.
 */
abstract class BaseCursorAdapter<ModelType, ViewType extends View & DataView<ModelType>> extends
        Adapter<BaseViewHolder<ViewType>> {

    protected IterableCursor<ModelType> mIterableCursor;

    public void setCursor(IterableCursor<ModelType> cursor) {
        if (this.mIterableCursor != null) {
            mIterableCursor.close();
        }
        this.mIterableCursor = cursor;
        notifyDataSetChanged();
    }

    abstract public BaseViewHolder<ViewType> onCreateViewHolder(ViewGroup parent, int viewType);

    public ModelType getData(int position) {
        mIterableCursor.moveToPosition(position);
        return mIterableCursor.peek();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ViewType> holder, int position) {
        holder.getView().setContent(getData(position));
    }

    @Override
    public int getItemCount() {
        return (mIterableCursor == null) ? 0 : mIterableCursor.getCount();
    }
}
