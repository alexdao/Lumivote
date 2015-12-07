package com.lumivote.lumivote.ui.bills_tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lumivote.lumivote.R;
import com.lumivote.lumivote.api.SunlightRESTClient;
import com.lumivote.lumivote.api.sunlight_responses.bills.Result;
import com.lumivote.lumivote.bus.BusProvider;
import com.lumivote.lumivote.bus.SunlightBillsEvent;
import com.lumivote.lumivote.ui.DividerItemDecoration;
import com.lumivote.lumivote.ui.detail_fragments.BillDetailsFragment;
import com.lumivote.lumivote.ui.detail_fragments.TimelineDetailsFragment;
import com.lumivote.lumivote.ui.timeline_tab.TimelineDataAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Alex Dao on May 24, 2015.
 */
public class BillsFragment extends Fragment {

    private List<Result> bills = new ArrayList<>();
    private List<BillsDataAdapter> billsDataAdapter = new ArrayList<>();

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    LinearLayoutManager llm;
    RVAdapter adapter;

    public BillsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bills, container, false);
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
        SunlightRESTClient client = SunlightRESTClient.getInstance();
        client.fetchBills(1);
    }

    @Subscribe
    public void handleSunlightBillsEvent(SunlightBillsEvent event) {
        bills = event.getVotesList();
        setData();
        adapter.notifyDataSetChanged();
    }

    private void setData() {
        for (int i = 0; i < bills.size(); i++) {
            Result result = bills.get(i);
            BillsDataAdapter temp = new BillsDataAdapter(result.getBillType(), result.getNumber().toString(), result.getChamber(), result.getOfficialTitle(), result.getLastActionAt());
            this.billsDataAdapter.add(temp);
        }
    }

    private void initializeRecyclerView(View view) {
        adapter = new RVAdapter(billsDataAdapter, view);
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

        List<BillsDataAdapter> billsDataAdapter;
        View view;

        RVAdapter(List<BillsDataAdapter> billsDataAdapter, View view) {
            this.billsDataAdapter = billsDataAdapter;
            this.view = view;
        }

        public void clear() {
            billsDataAdapter.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return billsDataAdapter.size();
        }

        @Override
        public SunlightDataViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_bills, viewGroup, false);
            SunlightDataViewHolder pvh = new SunlightDataViewHolder(v, new SunlightDataViewHolder.ISunlightDataViewHolderClicks() {
                public void onClickItem(View caller) {
                    AppCompatActivity host = (AppCompatActivity) view.getContext();
                    RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycler_view);
                    int itemPosition = rv.getChildAdapterPosition(caller);

                    BillsDataAdapter adapter = billsDataAdapter.get(itemPosition);
                    BillDetailsFragment detailsFragment = new BillDetailsFragment();
                    detailsFragment.show(host.getSupportFragmentManager(), R.id.bottomsheet);
                    detailsFragment.sendData(adapter);
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(SunlightDataViewHolder sunlightDataViewHolder, int i) {
            sunlightDataViewHolder.mainTitle.setText(billsDataAdapter.get(i).getMainTitle());
            sunlightDataViewHolder.mainDescription.setText(billsDataAdapter.get(i).getMainDescription());
            sunlightDataViewHolder.leftTitle.setText(billsDataAdapter.get(i).getLeftTitle());
            sunlightDataViewHolder.leftDescription.setText(billsDataAdapter.get(i).getLeftDescription());
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
