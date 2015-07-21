package com.crazydude.sakuraplayer.gui.views;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.DataView;
import com.crazydude.sakuraplayer.models.net.ArtistResponse;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Crazy on 06.06.2015.
 */
@EViewGroup(R.layout.view_recommended_artist)
public class RecommendedArtistView extends RelativeLayout implements DataView<ArtistResponse> {

    @ViewById(R.id.view_recommended_artist_image)
    ImageView mImageView;

    @ViewById(R.id.view_recommended_artist_name)
    TextView mArtistName;

    public RecommendedArtistView(Context context) {
        super(context);
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    public void setContent(ArtistResponse data) {
        mArtistName.setText(data.getName());
    }

    public void setTextColors(int textColor, int textBackground) {
        mArtistName.setTextColor(textColor);
        GradientDrawable drawable = (GradientDrawable) mArtistName.getBackground();
        drawable.setColor(textBackground);
    }
}
