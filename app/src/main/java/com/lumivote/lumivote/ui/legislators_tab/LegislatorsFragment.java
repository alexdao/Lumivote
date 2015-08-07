package com.lumivote.lumivote.ui.legislators_tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lumivote.lumivote.R;
import com.lumivote.lumivote.api.SunlightRESTClient;
import com.lumivote.lumivote.api.sunlight_responses.legislators.Result;
import com.lumivote.lumivote.bus.BusProvider;
import com.lumivote.lumivote.bus.SunlightLegislatorsEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LegislatorsFragment extends Fragment {

    public static final String TAG = "legislators";

    private List<Result> legislators = new ArrayList<>();
    private List<Data> data = new ArrayList<>();

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    LinearLayoutManager llm;
    RVAdapter adapter;

    public LegislatorsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_legislators, container, false);
        ButterKnife.bind(this, v);
        hideTabLayout();

        fetchData();
        initializeRecyclerView();

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

    private void fetchData() {
        SunlightRESTClient client = SunlightRESTClient.getInstance();
        client.fetchLegislators(1);
    }

    @Subscribe
    public void handleSunlightVotesEvent(SunlightLegislatorsEvent event) {
        legislators = event.getLegislators();
        setData();
        adapter.notifyDataSetChanged();
    }

    private void setData() {
        for (int i = 0; i < legislators.size(); i++) {
            Result result = legislators.get(i);
            Data temp = new Data(result.getTitle(), result.getChamber(), result.getFirstName(), result.getParty());
            this.data.add(temp);
        }
    }

    private void initializeRecyclerView() {
        adapter = new RVAdapter(data);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    class Data {
        String mainTitle;
        String mainDescription;
        String leftTitle;
        String leftDescription;

        Data(String mainTitle, String mainDescription, String leftTitle, String leftDescription) {
            this.mainTitle = mainTitle;
            this.mainDescription = mainDescription;
            this.leftTitle = leftTitle;
            this.leftDescription = leftDescription;
        }
    }

    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.SunlightDataViewHolder> {

        List<Data> data;

        RVAdapter(List<Data> data) {
            this.data = data;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public SunlightDataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_legislators, viewGroup, false);
            SunlightDataViewHolder pvh = new SunlightDataViewHolder(v, new SunlightDataViewHolder.ISunlightDataViewHolderClicks() {
                public void onClickItem(View caller) {
                    Log.d("Hello", "test");
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(SunlightDataViewHolder sunlightDataViewHolder, int i) {
            sunlightDataViewHolder.mainTitle.setText(data.get(i).mainTitle);
            sunlightDataViewHolder.mainDescription.setText(data.get(i).mainDescription);
            sunlightDataViewHolder.leftTitle.setText(data.get(i).leftTitle);
            sunlightDataViewHolder.leftDescription.setText(data.get(i).leftDescription);
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