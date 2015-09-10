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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;
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

    public TimelineFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timeline, container, false);
        hideTabLayout();
        ButterKnife.bind(this, v);

        fetchData();
        initializeRecyclerView(v);

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

    private void hideTabLayout() {
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.GONE);
        ViewPager mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        mViewPager.setVisibility(View.GONE);
    }

    private void fetchData() {
        LumivoteRESTClient client = LumivoteRESTClient.getInstance();
        client.fetchTimelineEvents();
    }

    @Subscribe
    public void handleLumivoteTimelineEvent(LumivoteTimelineEvent event) {
        timelineData = event.getTimelineList();
        setData();
        adapter.notifyDataSetChanged();
    }

    private void setData() {
        for (int i = 0; i < timelineData.size(); i++) {
            Timeline timelineEvent = timelineData.get(i);
            TimelineDataAdapter temp = new TimelineDataAdapter(timelineEvent.getName(), timelineEvent.getDescription(), timelineEvent.getCity(), timelineEvent.getDate());
            this.formattedData.add(temp);
        }
    }

    private void initializeRecyclerView(View view) {
        adapter = new RVAdapter(formattedData, view);
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

    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.TimelineDataViewHolder> {

        List<TimelineDataAdapter> timelineData;
        View view;

        RVAdapter(List<TimelineDataAdapter> timelineData, View view) {
            this.timelineData = timelineData;
            this.view = view;
        }

        public void clear() {
            timelineData.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return timelineData.size();
        }

        @Override
        public TimelineDataViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_timeline, viewGroup, false);
            TimelineDataViewHolder pvh = new TimelineDataViewHolder(v, new TimelineDataViewHolder.ISunlightDataViewHolderClicks() {
                public void onClickItem(View caller) {
                    BottomSheetLayout bottomSheetLayout = (BottomSheetLayout) view.findViewById(R.id.bottomsheet);
                    bottomSheetLayout.showWithSheetView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_timeline_details, bottomSheetLayout, false));
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(TimelineDataViewHolder timelineDataViewHolder, int i) {
            timelineDataViewHolder.mainTitle.setText(timelineData.get(i).getMainTitle());
            timelineDataViewHolder.mainDescription.setText(timelineData.get(i).getMainDescription());
            timelineDataViewHolder.leftTitle.setText(timelineData.get(i).getLeftTitle());
            timelineDataViewHolder.leftDescription.setText(timelineData.get(i).getLeftDescription());
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class TimelineDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            RelativeLayout relativeLayout;
            TextView leftTitle;
            TextView leftDescription;
            TextView mainTitle;
            TextView mainDescription;
            public ISunlightDataViewHolderClicks mListener;

            TimelineDataViewHolder(View itemView, ISunlightDataViewHolderClicks listener) {
                super(itemView);
                mListener = listener;
                relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
                relativeLayout.setOnClickListener(this);
                leftTitle = ButterKnife.findById(itemView, R.id.leftTitle);
                leftDescription = ButterKnife.findById(itemView, R.id.leftDescription);
                mainTitle = ButterKnife.findById(itemView, R.id.mainTitle);
                mainDescription = ButterKnife.findById(itemView, R.id.mainDescription);
            }

            @Override
            public void onClick(View v) {
                mListener.onClickItem(v);
            }

            public interface ISunlightDataViewHolderClicks {
                void onClickItem(View caller);
            }
        }
    }
}
