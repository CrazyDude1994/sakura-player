package com.crazydude.sakuraplayer.gui.fragments;

import android.support.v7.widget.RecyclerView;

import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.views.fragments.RecommendsFragmentView;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by CrazyDude on 17.03.2015.
 */
@EFragment(R.layout.fragment_recommendations)
public class RecommendsFragment extends BaseFragment {

    @Bean
    RecommendsFragmentView mRecommendsFragmentView;

    @ViewById(R.id.fragment_recommendations_recycler)
    RecyclerView mRecyclerView;

    @AfterViews
    void initViews() {
    }
}
