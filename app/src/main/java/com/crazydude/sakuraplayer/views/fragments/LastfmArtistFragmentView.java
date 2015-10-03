package com.crazydude.sakuraplayer.views.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.graphics.Palette;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.models.net.ArtistInfoResponse;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.BindColor;

/**
 * Created by kartavtsev.s on 08.06.2015.
 */
public class LastfmArtistFragmentView extends BaseFragmentView implements Palette.PaletteAsyncListener,
        Target {

    @Bind(R.id.fragment_lastfm_artist_image)
    ImageView mArtistImage;

    @Bind(R.id.fragment_lastfm_artist_name)
    TextView mArtistName;

    @Bind(R.id.fragment_lastfm_artist_tag_layout)
    FlowLayout mTagLayout;

    @Bind(R.id.fragment_lastfm_artist_header)
    RelativeLayout mHeaderLayout;

    @Bind(R.id.fragment_lastfm_artist_summary)
    TextView mSummaryText;

    @Bind(R.id.fragment_lastfm_artist_layout)
    LinearLayout mLinearLayout;

    @Bind(R.id.fragment_lastfm_artist_similar_text)
    TextView mSimilarText;

    @BindColor(R.color.white)
    int mWhiteColor;

    private ArrayList<TextView> mTagsViews = new ArrayList<>();


    private TextView generateTagTextView(String text) {
        TextView textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.view_tag, null);
        FlowLayout.LayoutParams layoutParams = new
                FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int tagMargin = mContext.getResources().getDimensionPixelSize(R.dimen.ui_elements_margin) / 4;
        layoutParams.setMargins(tagMargin, tagMargin, tagMargin, tagMargin);
        textView.setText(text);
        textView.setTextColor(mWhiteColor);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    public void setData(ArtistInfoResponse data) {
        mArtistName.setText(data.getArtist().getName());
        mSummaryText.setText(Html.fromHtml(data.getArtist().getBio().getSummary()));
        for (ArtistInfoResponse.Artist.Tags.Tag tag : data.getArtist().getTags().getTag()) {
            TextView textView = generateTagTextView(tag.getName());
            mTagLayout.addView(textView);
            mTagsViews.add(textView);
        }
    }

    public void showContent() {
        mLinearLayout.setVisibility(View.VISIBLE);
    }

    public void hideContent() {
        mLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void onGenerated(Palette palette) {
        int swatchNumber = 0;
        for (TextView tag : mTagsViews) {
            GradientDrawable drawable = (GradientDrawable) tag.getBackground();
            Palette.Swatch currentSwatch = palette.getSwatches().get(swatchNumber);
            drawable.setColor(currentSwatch.getRgb());
            tag.setTextColor(currentSwatch.getTitleTextColor());
            swatchNumber++;
            if (swatchNumber >= palette.getSwatches().size()) {
                swatchNumber = 0;
            }
        }
        Palette.Swatch backgroundSwatch = palette.getSwatches().get(0);
        mLinearLayout.setBackgroundColor(backgroundSwatch.getRgb());
        mSummaryText.setTextColor(backgroundSwatch.getBodyTextColor());
        mSimilarText.setTextColor(backgroundSwatch.getTitleTextColor());
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        mArtistImage.setImageBitmap(bitmap);
        Palette.generateAsync(bitmap, this);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
