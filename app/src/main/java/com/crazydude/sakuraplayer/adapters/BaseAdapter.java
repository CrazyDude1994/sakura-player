package com.crazydude.sakuraplayer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by Crazy on 16.05.2015.
 */
abstract class BaseAdapter<ModelType> extends Adapter<ViewHolder> {

    private List<ModelType> mData;


    abstract public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);
    abstract public void onBindViewHolder(ViewHolder holder, int position);

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
