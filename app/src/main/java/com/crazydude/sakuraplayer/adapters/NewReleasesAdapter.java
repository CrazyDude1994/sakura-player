package com.crazydude.sakuraplayer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.AlbumView;
import com.crazydude.sakuraplayer.gui.views.AlbumView_;
import com.crazydude.sakuraplayer.gui.views.RecommendedArtistView;
import com.crazydude.sakuraplayer.gui.views.RecommendedArtistView_;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;
import com.squareup.picasso.Picasso;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
public class NewReleasesAdapter extends BaseAdapter<AlbumResponse.Album> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AlbumView albumView = AlbumView_.build(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(albumView, parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumResponse.Album data = getData(position);
        ViewHolder viewHolder = ((ViewHolder) holder);
        AlbumView albumView = viewHolder.getView();
        albumView.setData(data);
        String imageUrl = data.getImage().get(3).getUrl();
        Picasso.with(viewHolder.getContext()).load(imageUrl).into(albumView.getImageView());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private AlbumView mImageView;
        private Context mContext;

        public ViewHolder(AlbumView itemView, Context context) {
            super(itemView);
            mImageView = itemView;
            mContext = context;
        }

        public Context getContext() {
            return mContext;
        }

        public AlbumView getView() {
            return mImageView;
        }

    }
}
