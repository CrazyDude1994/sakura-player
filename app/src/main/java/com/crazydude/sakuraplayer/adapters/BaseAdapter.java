package com.crazydude.sakuraplayer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.interfaces.DataView;

import org.androidannotations.api.view.HasViews;

import java.util.List;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by Crazy on 16.05.2015.
 */
abstract class BaseAdapter<ModelType, ViewType extends View & DataView<ModelType>> extends
        Adapter<BaseViewHolder<ViewType>> {

    private List<ModelType> mData;

    abstract public BaseViewHolder<ViewType> onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(BaseViewHolder<ViewType> holder, int position) {
        holder.getView().setContent(mData.get(position));
    }

    public void setData(List<ModelType> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public ModelType getData(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return (mData == null) ? 0 : mData.size();
    }
}
