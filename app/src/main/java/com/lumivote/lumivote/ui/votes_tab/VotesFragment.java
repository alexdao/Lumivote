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
import com.lumivote.lumivote.api.sunlight_responses.votes.Result;
import com.lumivote.lumivote.workhorse.SunlightRecyclerView;

import java.util.List;

public class VotesFragment extends Fragment {

    List<Result> votes;

    public VotesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sunlight_data, container, false);
        hideTabLayout();
        fetchData();
        setUpRecyclerView(v);

        return v;
    }

    private void hideTabLayout(){
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.GONE);
        ViewPager mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        mViewPager.setVisibility(View.GONE);
    }

    private void fetchData(){
        SunlightRESTClient test = new SunlightRESTClient();
        test.fetchVotes(1);
        votes = test.votes_list;
    }

    private void setUpRecyclerView(View v){
        SunlightRecyclerView rv = new SunlightRecyclerView(this, v);
        rv.initializeRecyclerView();
    }
}
