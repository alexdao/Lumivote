package com.lumivote.lumivote.ui.timeline_tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lumivote.lumivote.R;
import com.lumivote.lumivote.api.LumivoteRESTClient;
import com.lumivote.lumivote.api.lumivote_responses.timeline.Timeline;
import com.lumivote.lumivote.bus.BusProvider;
import com.lumivote.lumivote.bus.LumivoteTimelineEvent;
import com.lumivote.lumivote.ui.DividerItemDecoration;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TimelineFragment extends Fragment {

    private List<Timeline> timelineData = new ArrayList<>();
    private List<TimelineDataAdapter> formattedData = new ArrayList<>();

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    LinearLayoutManager llm;
    RVAdapter adapter;

    View view;

    public TimelineFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timeline, container, false);
        hideTabLayout();
        ButterKnife.bind(this, v);
        view = v;

        fetchData();

        return v;
    }

    private void fetchData() {
        LumivoteRESTClient client = LumivoteRESTClient.getInstance();
        client.fetchTimelineEvents();
    }

    @Subscribe
    public void handleLumivoteTimelineEvent(LumivoteTimelineEvent event) {
        timelineData = event.getTimelineList();
        setData();
        initializeRecyclerView(view);
        adapter.notifyDataSetChanged();
    }

    private void setData() {
        for (int i = 0; i < timelineData.size(); i++) {
            Timeline timelineEvent = timelineData.get(i);
            TimelineDataAdapter temp = new TimelineDataAdapter(timelineEvent.getName(), timelineEvent.getDate(), timelineEvent.getParty());
            this.formattedData.add(temp);
        }
    }

    private void initializeRecyclerView(View view) {
        Log.d("HERE I AM", "" + timelineData.size() + " " + formattedData.size());
        adapter = new RVAdapter(formattedData, timelineData, view);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        initializeSwipeRefreshLayout();
    }

    private void initializeSwipeRefreshLayout() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                fetchData();
                swipeContainer.setRefreshing(false);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
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

    private void hideTabLayout() {
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.GONE);
        ViewPager mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        mViewPager.setVisibility(View.GONE);
    }
}
