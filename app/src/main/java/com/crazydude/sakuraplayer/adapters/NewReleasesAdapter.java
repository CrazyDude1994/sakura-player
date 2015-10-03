package com.crazydude.sakuraplayer.adapters;

import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.AlbumView;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;
import com.squareup.picasso.Picasso;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
public class NewReleasesAdapter extends BaseAdapter<AlbumResponse.Album, AlbumView> {

    @Override
    public BaseViewHolder<AlbumView> onCreateViewHolder(ViewGroup parent, int viewType) {
        AlbumView albumView = new AlbumView(mContext);
        return new BaseViewHolder<>(albumView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<AlbumView> holder, int position) {
        AlbumResponse.Album data = getData(position);
        AlbumView albumView = holder.getView();
        albumView.setContent(data);
        String imageUrl = data.getImage().get(3).getUrl();
        Picasso.with(holder.getView().getContext()).load(imageUrl).into(albumView.getImageView());
    }
}
