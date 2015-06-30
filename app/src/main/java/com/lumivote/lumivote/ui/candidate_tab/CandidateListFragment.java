package com.lumivote.lumivote.ui.candidate_tab;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.lumivote.lumivote.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Alex Dao on May 23, 2015.
 */
public class CandidateListFragment extends Fragment {
    public static final String TAG = "candidates";

   // @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    ViewPager mViewPager;
    TabsFragmentPagerAdapter pagerAdapter;

    public CandidateListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagerAdapter = new TabsFragmentPagerAdapter(getActivity().getSupportFragmentManager(),
                getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_candidatelist, container, false);
        initInstances(v);
        return v;
    }

    private void initInstances(View v){
        //TODO: Change to support below API level 21
        //getActivity().findViewById(R.id.toolbar_actionbar).setElevation(R.dimen.toolbar_candidatelist_elevation);
        mViewPager = (ViewPager) v.findViewById(R.id.viewpager);
        mViewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        //ButterKnife.inject(this, v);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public static class TabsFragmentPagerAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };
        private Context context;

        public TabsFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
            tabTitles[0] = context.getString(R.string.candidates_democrat);
            tabTitles[1] = context.getString(R.string.candidates_republican);
            tabTitles[2] = context.getString(R.string.candidates_independent);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            return CandidatePartyFragment.newInstance(position+1);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
