package com.crazydude.sakuraplayer.gui.fragments;

import android.widget.ListView;
import com.crazydude.sakuraplayer.R;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

/**
 * Created by CrazyDude on 17.03.2015.
 */
@EFragment
public class RecommendsFragment extends BaseFragment {

    @ViewById(R.id.fragment_recommendations_list)
    ListView mListView;

    @AfterViews
    void initViews() {
    }

}
