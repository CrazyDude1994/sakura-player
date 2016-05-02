package com.crazydude.sakuraplayer.gui.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.DataView;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
public class AlbumCardView extends RelativeLayout implements DataView<AlbumResponse.Album> {

    @Bind(R.id.view_album_card_image)
    ImageView mImageView;

    @Bind(R.id.view_album_card_artist_name)
    TextView mArtistName;

    @Bind(R.id.view_album_card_name)
    TextView mAlbumName;

    @Bind(R.id.view_album_card_date)
    TextView mAlbumDate;

    public AlbumCardView(Context context) {
        super(context);
        ButterKnife.bind(this);
    }

    public ImageView getImageView() {
        return mImageView;
    }

    @Override
    public void setContent(AlbumResponse.Album data) {
        mArtistName.setText(data.getArtist().getName());
        mAlbumName.setText(data.getName());
//        mAlbumDate.setText(data.get);
    }
}
