package com.crazydude.sakuraplayer.views.fragments;

import android.content.Context;
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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;
import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;

/**
 * Created by kartavtsev.s on 08.06.2015.
 */
@EBean
public class ArtistFragmentView extends BaseFragmentView implements Palette.PaletteAsyncListener,
        Target {

    @RootContext
    Context mContext;

    @ViewById(R.id.fragment_artist_image)
    ImageView mArtistImage;

    @ViewById(R.id.fragment_artist_name)
    TextView mArtistName;

    @ViewById(R.id.fragment_artist_tag_layout)
    FlowLayout mTagLayout;

    @ViewById(R.id.fragment_artist_header)
    RelativeLayout mHeaderLayout;

    @ViewById(R.id.fragment_artist_summary)
    TextView mSummaryText;

    @ViewById(R.id.fragment_artist_layout)
    LinearLayout mLinearLayout;

    @ViewById(R.id.fragment_artist_similar_text)
    TextView mSimilarText;

    @ColorRes(R.color.white)
    int mWhiteColor;

    private ArrayList<TextView> mTagsViews = new ArrayList<>();

    @AfterViews
    void initViews() {
    }

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

    @UiThread
    public void setData(ArtistInfoResponse data) {
        mArtistName.setText(data.getArtist().getName());
        mSummaryText.setText(Html.fromHtml(data.getArtist().getBio().getSummary()));
        for (ArtistInfoResponse.Artist.Tags.Tag tag : data.getArtist().getTags().getTag()) {
            TextView textView = generateTagTextView(tag.getName());
            mTagLayout.addView(textView);
            mTagsViews.add(textView);
        }
    }

    @UiThread
    public void showContent() {
        mLinearLayout.setVisibility(View.VISIBLE);
    }

    @UiThread
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
