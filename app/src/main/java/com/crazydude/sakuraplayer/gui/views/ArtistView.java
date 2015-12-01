package com.crazydude.sakuraplayer.gui.views;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.andexert.library.RippleView;
import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.DataView;
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

    @Bind(R.id.view_artist_ripple_view)
    RippleView mRippleView;

    @BindColor(R.color.accent)
    int mCircleColor;

    public ArtistView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_artist, this);
        ButterKnife.bind(this);
    }

    public void setRippleCallback(RippleView.OnRippleCompleteListener listener) {
        mRippleView.setOnRippleCompleteListener(listener);
    }

    public void setContent(ArtistModel data) {
        if (data != null) {
            mArtistNameText.setText(data.getArtistName());
            if (!data.getArtistArt().isEmpty()) {
                File file = new File(data.getArtistArt());
                Picasso.with(getContext()).load(file).resizeDimen(R.dimen.list_rounded_image_width_height, R.dimen.list_rounded_image_width_height).into(mImageView);
            } else {
                TextDrawable textDrawable = TextDrawable.builder().buildRound(data.getArtistName().substring(0, 1), mCircleColor);
                mImageView.setImageDrawable(textDrawable);
            }
        }
    }
}
