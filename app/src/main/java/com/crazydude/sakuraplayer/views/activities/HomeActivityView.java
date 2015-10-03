package com.crazydude.sakuraplayer.views.activities;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindColor;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.OnAfterSplashScreenListener;

/**
 * Created by Crazy on 16.05.2015.
 */
public class HomeActivityView extends BaseActivityView implements Animator.AnimatorListener {

    @Inject
    AppCompatActivity mActivity;

    @Bind(R.id.activity_home_splash_screen)
    View mSplashScreenImage;

    @Bind(R.id.activity_home_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.navigation_drawer)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.activity_home_view_player_widget)
    RelativeLayout mPlayerWidget;

    @Bind(R.id.view_player_widget_artist)
    TextView mPlayerWidgetArtist;

    @Bind(R.id.view_player_widget_song)
    TextView mPlayerWidgetSong;

    @Bind(R.id.view_player_widget_equalizer_image)
    ImageView mEqualizerImage;

    @Bind(R.id.view_player_widget_album_art)
    CircularImageView mWidgetAlbumArt;

    @BindColor(R.color.accent)
    int mAccentColor;

    private OnAfterSplashScreenListener mOnAfterSplashScreen;
    private ActionBarDrawerToggle mDrawerToggle;

    public HomeActivityView() {
        super();

        initViews();
    }

    void initViews() {
        initToolbar();
        initNavigationDrawer();
    }

    public void setOnAfterSplashScreenListener(OnAfterSplashScreenListener listener) {
        mOnAfterSplashScreen = listener;
    }

    public void setPlayerWidgetData(TrackModel trackModel) {
        mPlayerWidgetArtist.setText(trackModel.getArtist().getArtistName());
        mPlayerWidgetSong.setText(trackModel.getTrackName());
        if (trackModel.getAlbum() != null && trackModel.getAlbum().getAlbumArtPath() != null) {
            File file = new File(trackModel.getAlbum().getAlbumArtPath());
            Picasso.with(mContext)
                    .load(file)
                    .resizeDimen(R.dimen.list_rounded_image_width_height, R.dimen.list_rounded_image_width_height)
                    .error(TextDrawable.builder().buildRound(trackModel.getTrackName().substring(0, 1), mAccentColor))
                    .into(mWidgetAlbumArt);
        } else {
            mWidgetAlbumArt.setImageDrawable(TextDrawable.builder().buildRound(trackModel.getTrackName().substring(0, 1), mAccentColor));
        }
    }

    public void showPlayerWidget() {
        mPlayerWidget.setVisibility(View.VISIBLE);
        AnimationDrawable animationDrawable = (AnimationDrawable) mEqualizerImage.getBackground();
        animationDrawable.start();
    }

    public void hidePlayerWidget() {
        mPlayerWidget.setVisibility(View.GONE);
    }

    public void showToolbar() {
        mToolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        mToolbar.setVisibility(View.INVISIBLE);
    }

    @Deprecated
    public void showToolbarShadow() {

    }

    @Deprecated
    public void hideToolbarShadow() {

    }

    private void initToolbar() {
        mActivity.setSupportActionBar(mToolbar);
    }

    private void initNavigationDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(mActivity, mDrawerLayout, mToolbar,
                R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    public void hideSplashScreen() {
        mSplashScreenImage.setVisibility(View.GONE);
    }

    public void hideSplashScreen(final int duration) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator anim = ObjectAnimator.ofFloat(mSplashScreenImage, "alpha", 1f, 0f);
                anim.setDuration(500);
                anim.addListener(HomeActivityView.this);
                anim.start();
            }
        }, duration);
    }

    public void closeNavigationDrawer() {
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mSplashScreenImage.setVisibility(View.GONE);
        if (mOnAfterSplashScreen != null) {
            mOnAfterSplashScreen.onAfterSplashScreen();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
