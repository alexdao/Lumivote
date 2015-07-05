package com.lumivote.lumivote.ui.candidate_tab;

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
public class CandidateListFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager mViewPager;

    public CandidateListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_candidatelist, container, false);
        initInstances(v);
        return v;
    }

    private void initInstances(View v){
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.VISIBLE);
        mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        mViewPager.setVisibility(View.VISIBLE);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
    }

}
