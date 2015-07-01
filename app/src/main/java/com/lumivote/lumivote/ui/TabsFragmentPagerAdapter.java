package com.lumivote.lumivote.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lumivote.lumivote.R;
import com.lumivote.lumivote.ui.candidate_tab.CandidatePartyFragment;

/**
 * Created by alex on 6/30/15.
 */
public class TabsFragmentPagerAdapter extends FragmentPagerAdapter {

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
        return CandidatePartyFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}