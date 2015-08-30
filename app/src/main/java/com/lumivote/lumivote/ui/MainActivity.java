package com.lumivote.lumivote.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lumivote.lumivote.R;
import com.lumivote.lumivote.ui.about_tab.AboutFragment;
import com.lumivote.lumivote.ui.bills_tab.BillsFragment;
import com.lumivote.lumivote.ui.candidate_tab.CandidateListFragment;
import com.lumivote.lumivote.ui.candidate_tab.CandidatePartyFragment;
import com.lumivote.lumivote.ui.legislators_tab.LegislatorsFragment;
import com.lumivote.lumivote.ui.settings_tab.SettingsFragment;
import com.lumivote.lumivote.ui.starred_tab.StarredFragment;
import com.lumivote.lumivote.ui.timeline_tab.TimelineFragment;
import com.lumivote.lumivote.ui.votes_tab.VotesFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ActionBarDrawerToggle drawerToggle;

    @Bind(R.id.navigation)
    NavigationView navigation;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initInstances();
        initTabLayout();
        setupDrawerContent(navigation);

        //defaults to drawer open with the timeline fragment instantiated
        drawerLayout.openDrawer(Gravity.LEFT);

        Fragment fragment = null;
        try {
            fragment = TimelineFragment.class.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        setTitle(getResources().getString(R.string.timeline_title));

    }

    private void initInstances() {
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initTabLayout() {
        TabsFragmentPagerAdapter pagerAdapter = new TabsFragmentPagerAdapter(this.getSupportFragmentManager(),
                this);
        mViewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;

        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.timeline:
                fragmentClass = TimelineFragment.class;
                break;
            case R.id.candidates:
                fragmentClass = CandidateListFragment.class;
                break;
            case R.id.starred:
                fragmentClass = StarredFragment.class;
                break;
            case R.id.legislators:
                fragmentClass = LegislatorsFragment.class;
                break;
            case R.id.bills:
                fragmentClass = BillsFragment.class;
                break;
            case R.id.votes:
                fragmentClass = VotesFragment.class;
                break;
            case R.id.settings:
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.about:
                fragmentClass = AboutFragment.class;
                break;
            default:
                fragmentClass = TimelineFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawers();
        else
            super.onBackPressed();
    }

    public static class TabsFragmentPagerAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[]{"Tab1", "Tab2", "Tab3"};
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
}
