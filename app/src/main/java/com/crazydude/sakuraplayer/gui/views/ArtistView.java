package com.crazydude.sakuraplayer.gui.views;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.DataView;
import com.crazydude.sakuraplayer.models.AlbumModel;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

/**
 * Created by Crazy on 27.05.2015.
 */
public class ArtistView extends RelativeLayout implements DataView<ArtistModel> {

    @Bind(R.id.view_artist_name_text)
    TextView mArtistNameText;

    @Bind(R.id.view_artist_image)
    CircularImageView mImageView;

    @BindColor(R.color.accent)
    int mCircleColor;

    public ArtistView(Context context) {
        super(context);
        ButterKnife.bind(this);
    }

    public void setContent(ArtistModel data) {
        if (data != null) {
            mArtistNameText.setText(data.getArtistName());
            boolean loaded = false;
            if (data.getAlbums().size() > 0) {
                for (AlbumModel albumModel : data.getAlbums()) {
                    if (albumModel.getAlbumArtPath() != null && !albumModel.getAlbumArtPath().isEmpty()) {
                        File file = new File(albumModel.getAlbumArtPath());
                        Picasso.with(getContext()).load(file).resizeDimen(R.dimen.list_rounded_image_width_height, R.dimen.list_rounded_image_width_height).into(mImageView);
                        loaded = true;
                        break;
                    }
                }
            }
            if (!loaded) {
                TextDrawable textDrawable = TextDrawable.builder().buildRound(data.getArtistName().substring(0, 1), mCircleColor);
                mImageView.setImageDrawable(textDrawable);
            }
        }
    }
}
