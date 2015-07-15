package com.lumivote.lumivote.ui.about_tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lumivote.lumivote.R;

/**
 * Created by Alex Dao on May 23, 2015.
 */
public class AboutFragment extends Fragment {

    public AboutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewPager mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        mViewPager.setVisibility(View.GONE);
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.GONE);

        View view = inflater.inflate(R.layout.fragment_about, container, false);

        return view;
    }
}
