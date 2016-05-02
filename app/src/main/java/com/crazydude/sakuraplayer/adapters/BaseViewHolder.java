package com.crazydude.sakuraplayer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.crazydude.sakuraplayer.interfaces.DataView;

/**
 * Created by kartavtsev.s on 21.07.2015.
 */
public class BaseViewHolder<ViewType extends View & DataView> extends RecyclerView.ViewHolder {

    private ViewType mView;

    public BaseViewHolder(ViewType itemView) {
        super(itemView);
        mView = itemView;
    }

    public ViewType getView() {
        return mView;
    }
}
