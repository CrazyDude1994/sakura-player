package com.crazydude.sakuraplayer.gui.views;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.models.ArtistModel;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Crazy on 27.05.2015.
 */
@EViewGroup(R.layout.view_artist)
public class ArtistView extends RelativeLayout {

    @ViewById(R.id.view_artist_name_text)
    TextView mArtistNameText;

    public ArtistView(Context context) {
        super(context);
    }

    public void setContent(ArtistModel data) {
        if (data != null) {
            mArtistNameText.setText(data.getArtistName());
        }
    }
}
