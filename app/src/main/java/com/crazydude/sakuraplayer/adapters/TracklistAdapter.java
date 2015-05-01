package com.crazydude.sakuraplayer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.models.TrackModel;

import java.util.ArrayList;

/**
 * Created by Crazy on 26.04.2015.
 */
public class TracklistAdapter extends RecyclerView.Adapter<TracklistAdapter.ViewHolder> {

    private ArrayList<TrackModel> mContent;

    public TracklistAdapter(ArrayList<TrackModel> content) {
        mContent = content;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (mContent == null) ? 0 : mContent.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mItemView = itemView;
        }
    }
}
