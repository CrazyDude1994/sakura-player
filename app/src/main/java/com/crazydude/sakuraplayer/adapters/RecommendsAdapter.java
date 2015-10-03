package com.crazydude.sakuraplayer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.views.RecommendedArtistView;
import com.crazydude.sakuraplayer.models.net.ArtistResponse;
import com.squareup.picasso.Picasso;

/**
 * Created by Crazy on 06.06.2015.
 */
public class RecommendsAdapter extends BaseAdapter<ArtistResponse, RecommendedArtistView> {

    @Override
    public BaseViewHolder<RecommendedArtistView> onCreateViewHolder(ViewGroup parent, int viewType) {
        RecommendedArtistView recommendedArtistView = new RecommendedArtistView(mContext);
        return new BaseViewHolder<>(recommendedArtistView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<RecommendedArtistView> holder, int position) {
        ArtistResponse data = getData(position);
        holder.getView().setContent(data);
        String imageUrl = data.getImages().get(3).getUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.with(holder.getView().getContext())
                    .load(imageUrl)
                    .into(holder.getView().getmImageView());
        }
    }
}
