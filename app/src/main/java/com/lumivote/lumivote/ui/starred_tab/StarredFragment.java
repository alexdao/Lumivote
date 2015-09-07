package com.lumivote.lumivote.ui.starred_tab;

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

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.lumivote.lumivote.R;
import com.lumivote.lumivote.TinyDB;
import com.lumivote.lumivote.ui.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StarredFragment extends Fragment {

    private List<Data> starredData = new ArrayList<>();

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    LinearLayoutManager llm;
    RVAdapter adapter;

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
        ButterKnife.bind(this, v);
        hideTabLayout();

        setData();
        initializeRecyclerView();

        BottomSheetLayout bottomSheet = (BottomSheetLayout) v.findViewById(R.id.bottomsheet);
        bottomSheet.showWithSheetView(inflater.inflate(R.layout.fragment_starred_details, bottomSheet, false));

        return v;
    }

    private void hideTabLayout() {
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.GONE);
        ViewPager mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        mViewPager.setVisibility(View.GONE);
    }

    private void setData() {
        TinyDB tinyDB = new TinyDB(getActivity());
        HashSet<String> candidates = tinyDB.getSet(getResources().getString(R.string.starred_candidates_list));
        for (String candidate : candidates) {
            starredData.add(new Data(candidate, "main descrip", "leftTitle", "leftDescrip"));
        }
    }

    private void initializeRecyclerView() {
        adapter = new RVAdapter(starredData);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);
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

    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.StarredDataViewHolder> {

        List<Data> starredData;

        RVAdapter(List<Data> starredData) {
            this.starredData = starredData;
        }

        public void clear() {
            starredData.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return starredData.size();
        }

        @Override
        public StarredDataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_starred, viewGroup, false);
            StarredDataViewHolder pvh = new StarredDataViewHolder(v, new StarredDataViewHolder.ISunlightDataViewHolderClicks() {
                public void onClickItem(View caller) {
                    Log.d("Hello", "test");
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(StarredDataViewHolder starredDataViewHolder, int i) {
            starredDataViewHolder.mainTitle.setText(starredData.get(i).mainTitle);
            starredDataViewHolder.mainDescription.setText(starredData.get(i).mainDescription);
            starredDataViewHolder.leftTitle.setText(starredData.get(i).leftTitle);
            starredDataViewHolder.leftDescription.setText(starredData.get(i).leftDescription);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class StarredDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            RelativeLayout relativeLayout;
            TextView leftTitle;
            TextView leftDescription;
            TextView mainTitle;
            TextView mainDescription;
            public ISunlightDataViewHolderClicks mListener;

            StarredDataViewHolder(View itemView, ISunlightDataViewHolderClicks listener) {
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
