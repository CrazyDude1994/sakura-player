package com.crazydude.sakuraplayer.adapters;

import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.AlbumCardView;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;
import com.squareup.picasso.Picasso;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
public class NewReleasesAdapter extends BaseAdapter<AlbumResponse.Album, AlbumCardView> {

    @Override
    public BaseViewHolder<AlbumCardView> onCreateViewHolder(ViewGroup parent, int viewType) {
        AlbumCardView albumView = new AlbumCardView(mContext);
        return new BaseViewHolder<>(albumView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<AlbumCardView> holder, int position) {
        AlbumResponse.Album data = getData(position);
        AlbumCardView albumView = holder.getView();
        albumView.setContent(data);
        String imageUrl = data.getImage().get(3).getUrl();
        Picasso.with(holder.getView().getContext()).load(imageUrl).into(albumView.getImageView());
    }
}
