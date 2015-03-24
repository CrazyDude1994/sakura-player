package com.crazydude.sakuraplayer.gui.activity;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;
import com.crazydude.sakuraplayer.R;
import com.crazydude.sakuraplayer.gui.fragments.LastfmLoginFragment_;
import com.crazydude.sakuraplayer.models.events.SessionEvent;
import de.greenrobot.event.EventBus;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_home)
public class HomeActivity extends Activity {

    @ViewById(R.id.activity_home_progressbar)
    View mProgressBar;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @UiThread
    public void onEvent(SessionEvent session) {
        getFragmentManager().popBackStack();
        hideProgressBar();
        Toast.makeText(getApplication(), session.session, Toast.LENGTH_LONG).show();
    }

    @AfterViews
    void initViews() {
        getFragmentManager().beginTransaction()
                .replace(R.id.activity_home_placeholder, LastfmLoginFragment_.builder().build())
                .addToBackStack("login")
                .commit();
    }

    @UiThread
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @UiThread
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
