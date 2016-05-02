package com.crazydude.sakuraplayer.gui.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.interfaces.DataView;
import com.crazydude.sakuraplayer.models.AlbumModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kartavtsev.s on 15.06.2015.
 */
public class AlbumView extends RelativeLayout implements DataView<AlbumModel> {

    @Bind(R.id.view_album_image)
    ImageView mAlbumImage;

    @Bind(R.id.view_album_name_text)
    TextView mAlbumTextView;

    public AlbumView(Context context) {
        super(context);
        init();
    }

    void init() {
        inflate(getContext(), R.layout.view_album, this);
        ButterKnife.bind(this);
    }

    @Override
    public void setContent(AlbumModel data) {
        
    }
}
