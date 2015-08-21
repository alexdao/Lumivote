package com.lumivote.lumivote.ui.settings_tab;

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
import com.lumivote.lumivote.ui.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingsFragment extends Fragment {

    private List<String> settings_list = new ArrayList<>();

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    LinearLayoutManager llm;
    RVAdapter adapter;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, v);
        hideTabLayout();

        setData();
        initializeRecyclerView();

        return v;
    }

    private void hideTabLayout() {
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.GONE);
        ViewPager mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        mViewPager.setVisibility(View.GONE);
    }

    private void setData() {
        String[] settings = getResources().getStringArray(R.array.settings);
        Collections.addAll(settings_list, settings);
    }

    private void initializeRecyclerView() {
        adapter = new RVAdapter(settings_list);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);
    }

    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.SettingsViewHolder> {

        List<String> settings_list;

        RVAdapter(List<String> settings_list) {
            this.settings_list = settings_list;
        }

        public void clear() {
            settings_list.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return settings_list.size();
        }

        @Override
        public SettingsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_settings, viewGroup, false);
            SettingsViewHolder pvh = new SettingsViewHolder(v, new SettingsViewHolder.ISunlightDataViewHolderClicks() {
                public void onClickItem(View caller) {
                    Log.d("Hello", "test");
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(SettingsViewHolder settingsViewHolder, int i) {
            settingsViewHolder.leftTitle.setText(settings_list.get(i));
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class SettingsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            RelativeLayout relativeLayout;
            TextView leftTitle;
            public ISunlightDataViewHolderClicks mListener;

            SettingsViewHolder(View itemView, ISunlightDataViewHolderClicks listener) {
                super(itemView);
                mListener = listener;
                relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
                relativeLayout.setOnClickListener(this);
                leftTitle = ButterKnife.findById(itemView, R.id.leftTitle);
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
