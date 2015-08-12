package com.lumivote.lumivote.ui.starred_tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lumivote.lumivote.R;
import com.lumivote.lumivote.api.HuffPostPollRESTClient;
import com.lumivote.lumivote.api.huffpost_responses.republican_primary_polls.RepublicanPollResponse;
import com.lumivote.lumivote.bus.BusProvider;
import com.lumivote.lumivote.bus.HuffPostRepublicanPrimaryPollsEvent;
import com.squareup.otto.Subscribe;

public class StarredFragment extends Fragment {

    RepublicanPollResponse republicanPollResponse;

    public StarredFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_starred, container, false);
        hideTabLayout();

        HuffPostPollRESTClient client = HuffPostPollRESTClient.getInstance();
        client.fetchRepublicanPrimaryPolls();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    private void hideTabLayout(){
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.GONE);
        ViewPager mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        mViewPager.setVisibility(View.GONE);
    }

    @Subscribe
    public void handleSunlightBillsEvent(HuffPostRepublicanPrimaryPollsEvent event) {
        String test = event.getRepublicanPolls().get(0).getChoice();
        Log.v(test, "test");
    }
}
