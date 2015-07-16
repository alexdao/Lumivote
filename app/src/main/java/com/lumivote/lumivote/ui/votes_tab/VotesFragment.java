package com.lumivote.lumivote.ui.votes_tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lumivote.lumivote.R;
import com.lumivote.lumivote.api.SunlightRESTClient;

public class VotesFragment extends Fragment {

    public VotesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.GONE);
        ViewPager mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        mViewPager.setVisibility(View.GONE);

        /**SunlightRESTClient test = new SunlightRESTClient();
        test.fetchVotes(1);*/

        return inflater.inflate(R.layout.fragment_votes, container, false);
    }
}
