package com.crazydude.sakuraplayer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.views.RecommendedArtistView;
import com.crazydude.sakuraplayer.gui.views.RecommendedArtistView_;
import com.crazydude.sakuraplayer.models.net.ArtistResponse;
import com.squareup.picasso.Picasso;

/**
 * Created by Crazy on 06.06.2015.
 */
public class RecommendsAdapter extends BaseAdapter<ArtistResponse> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecommendedArtistView recommendedArtistView = RecommendedArtistView_.build(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(recommendedArtistView, parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ArtistResponse data = getData(position);
        ViewHolder viewHolder = ((ViewHolder) holder);
        RecommendedArtistView recommendedArtistView = viewHolder.getView();
        recommendedArtistView.setContent(data);
        String imageUrl = data.getImages().get(3).getUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.with(viewHolder.getContext())
                    .load(imageUrl)
                    .into(recommendedArtistView.getmImageView());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RecommendedArtistView mImageView;
        private Context mContext;

        public ViewHolder(RecommendedArtistView itemView, Context context) {
            super(itemView);
            mImageView = itemView;
            mContext = context;
        }

        public Context getContext() {
            return mContext;
        }

        public RecommendedArtistView getView() {
            return mImageView;
        }

    }
}
