package com.lumivote.lumivote.ui.votes_tab;

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
import com.lumivote.lumivote.api.SunlightRESTClient;
import com.lumivote.lumivote.api.sunlight_responses.votes.Result;
import com.lumivote.lumivote.bus.BusProvider;
import com.lumivote.lumivote.bus.SunlightVotesEvent;
import com.lumivote.lumivote.ui.DividerItemDecoration;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VotesFragment extends Fragment {

    private List<Result> votes = new ArrayList<>();
    private List<VotesDataAdapter> votesDataAdapter = new ArrayList<>();

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @Bind(R.id.bottomsheet)
    BottomSheetLayout bottomSheet;

    LinearLayoutManager llm;
    RVAdapter adapter;

    public VotesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_votes, container, false);

        ButterKnife.bind(this, v);
        hideTabLayout();

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
        SunlightRESTClient.getInstance().fetchVotes(1);
    }

    @Subscribe
    public void handleSunlightVotesEvent(SunlightVotesEvent event) {
        votes = event.getVotesList();
        setData();
        Log.d("event received", "hi");
        adapter.notifyDataSetChanged();
    }

    private void setData() {
        for (int i = 0; i < votes.size(); i++) {
            Result result = votes.get(i);
            VotesDataAdapter temp = new VotesDataAdapter(result.getBillId(), result.getResult(), result.getQuestion(), result.getVotedAt(), result.getChamber());
            this.votesDataAdapter.add(temp);
        }
    }

    private void initializeRecyclerView(View view) {
        adapter = new RVAdapter(votesDataAdapter, view);
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

    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.SunlightDataViewHolder> {

        List<VotesDataAdapter> votesDataAdapter;
        View view;

        RVAdapter(List<VotesDataAdapter> votesDataAdapter, View view) {
            this.votesDataAdapter = votesDataAdapter;
            this.view = view;
        }

        public void clear() {
            votesDataAdapter.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return votesDataAdapter.size();
        }

        @Override
        public SunlightDataViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_votes, viewGroup, false);
            SunlightDataViewHolder pvh = new SunlightDataViewHolder(v, new SunlightDataViewHolder.ISunlightDataViewHolderClicks() {
                public void onClickItem(View caller) {
                    BottomSheetLayout bottomSheetLayout = (BottomSheetLayout) view.findViewById(R.id.bottomsheet);
                    bottomSheetLayout.showWithSheetView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_vote_details, bottomSheetLayout, false));
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(SunlightDataViewHolder sunlightDataViewHolder, int i) {
            sunlightDataViewHolder.mainTitle.setText(votesDataAdapter.get(i).getMainTitle());
            sunlightDataViewHolder.mainDescription.setText(votesDataAdapter.get(i).getMainDescription());
            sunlightDataViewHolder.leftTitle.setText(votesDataAdapter.get(i).getLeftTitle());
            sunlightDataViewHolder.leftDescription.setText(votesDataAdapter.get(i).getLeftDescription());
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class SunlightDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            RelativeLayout relativeLayout;
            TextView leftTitle;
            TextView leftDescription;
            TextView mainTitle;
            TextView mainDescription;
            public ISunlightDataViewHolderClicks mListener;

            SunlightDataViewHolder(View itemView, ISunlightDataViewHolderClicks listener) {
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
