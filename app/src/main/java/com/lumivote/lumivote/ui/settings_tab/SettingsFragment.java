package com.lumivote.lumivote.ui.settings_tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lumivote.lumivote.R;
import com.lumivote.lumivote.ui.DividerItemDecoration;
import com.lumivote.lumivote.ui.MainActivity;
import com.lumivote.lumivote.ui.about_tab.AboutFragment;
import com.lumivote.lumivote.ui.starred_tab.StarredFragment;

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
            final View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_settings, viewGroup, false);
            final SettingsViewHolder pvh = new SettingsViewHolder(v);

            pvh.setListener(new SettingsViewHolder.ISettingsDataViewHolderClicks() {
                public void onClickItem(View caller) {
                    MainActivity context = (MainActivity) v.getContext();
                    String setting_selected = settings_list.get(pvh.position);

                    Fragment fragment = null;
                    Class fragmentClass;
                    if (setting_selected.equals("About")) {
                        fragmentClass = AboutFragment.class;
                    } else {
                        fragmentClass = StarredFragment.class;
                    }

                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    FragmentManager fragmentManager = context.getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.flContent, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(SettingsViewHolder settingsViewHolder, int i) {
            settingsViewHolder.settingName.setText(settings_list.get(i));
            settingsViewHolder.position = settingsViewHolder.getAdapterPosition();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class SettingsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            int position;
            RelativeLayout relativeLayout;
            TextView settingName;
            public ISettingsDataViewHolderClicks mListener;

            SettingsViewHolder(View itemView) {
                super(itemView);
                relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
                relativeLayout.setOnClickListener(this);
                settingName = ButterKnife.findById(itemView, R.id.leftTitle);
            }

            private void setListener(ISettingsDataViewHolderClicks listener) {
                mListener = listener;
                relativeLayout.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                mListener.onClickItem(v);
            }

            public interface ISettingsDataViewHolderClicks {
                void onClickItem(View caller);
            }
        }
    }
}
