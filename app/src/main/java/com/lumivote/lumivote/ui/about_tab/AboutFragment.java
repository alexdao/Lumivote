package com.lumivote.lumivote.ui.about_tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lumivote.lumivote.R;

/**
 * Created by Alex Dao on May 23, 2015.
 */
public class AboutFragment extends Fragment {
    public static final String TAG = "about";

    public AboutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**SunlightRESTClient test = new SunlightRESTClient();
        test.fetchVotes(1);*/
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}
