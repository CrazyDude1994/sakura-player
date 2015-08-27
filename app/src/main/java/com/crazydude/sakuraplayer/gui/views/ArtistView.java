package com.crazydude.sakuraplayer.gui.views;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.DataView;
import com.crazydude.sakuraplayer.models.AlbumModel;
import com.crazydude.sakuraplayer.models.ArtistModel;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import java.io.File;
import java.util.Random;

/**
 * Created by Crazy on 27.05.2015.
 */
@EViewGroup(R.layout.view_artist)
public class ArtistView extends RelativeLayout implements DataView<ArtistModel> {

    @ViewById(R.id.view_artist_name_text)
    TextView mArtistNameText;

    @ViewById(R.id.view_artist_image)
    CircularImageView mImageView;

    @ColorRes(R.color.accent)
    int mCircleColor;

    public ArtistView(Context context) {
        super(context);
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
