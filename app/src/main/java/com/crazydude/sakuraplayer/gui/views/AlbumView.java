package com.crazydude.sakuraplayer.gui.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.models.net.AlbumResponse;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
@EViewGroup(R.layout.view_album)
public class AlbumView extends RelativeLayout {

    @ViewById(R.id.view_album_image)
    ImageView mImageView;

    @ViewById(R.id.view_album_artist_name)
    TextView mArtistName;

    @ViewById(R.id.view_album_name)
    TextView mAlbumName;

    @ViewById(R.id.view_album_date)
    TextView mAlbumDate;

    public AlbumView(Context context) {
        super(context);
    }

    public void setData(AlbumResponse.Album data) {
        mArtistName.setText(data.getArtist().getName());
        mAlbumName.setText(data.getName());
//        mAlbumDate.setText(data.get);
    }

    public ImageView getImageView() {
        return mImageView;
    }
}
