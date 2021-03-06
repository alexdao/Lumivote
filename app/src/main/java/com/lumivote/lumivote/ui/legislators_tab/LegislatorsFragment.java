package com.lumivote.lumivote.ui.legislators_tab;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.lumivote.lumivote.R;
import com.lumivote.lumivote.api.SunlightRESTClient;
import com.lumivote.lumivote.api.UnitedStatesImagesURLBuilder;
import com.lumivote.lumivote.api.sunlight_responses.legislators.Result;
import com.lumivote.lumivote.bus.BusProvider;
import com.lumivote.lumivote.bus.SunlightLegislatorsEvent;
import com.lumivote.lumivote.ui.DividerItemDecoration;
import com.lumivote.lumivote.ui.candidate_tab.CircleTransform;
import com.lumivote.lumivote.ui.detail_fragments.LegislatorDetailsFragment;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LegislatorsFragment extends Fragment {

    private List<Result> legislators = new ArrayList<>();
    private List<LegislatorsDataAdapter> data = new ArrayList<>();

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    LinearLayoutManager llm;
    RVAdapter adapter;
    View view;

    public LegislatorsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_legislators, container, false);
        ButterKnife.bind(this, v);
        hideTabLayout();
        view = v;
        fetchData();

        return v;
    }

    private void fetchData() {
        SunlightRESTClient.getInstance().fetchLegislators(1);
    }

    @Subscribe
    public void handleSunlightVotesEvent(SunlightLegislatorsEvent event) {
        legislators = event.getLegislators();
        setData();
        initializeRecyclerView(view);
        adapter.notifyDataSetChanged();
    }

    private void setData() {
        for (int i = 0; i < legislators.size(); i++) {
            Result result = legislators.get(i);

            UnitedStatesImagesURLBuilder unitedStatesImagesURLBuilder = new UnitedStatesImagesURLBuilder();
            unitedStatesImagesURLBuilder.setBioID(result.getBioguideId());
            String url = unitedStatesImagesURLBuilder.getPhotoURL();

            LegislatorsDataAdapter temp = new LegislatorsDataAdapter(
                    result.getFirstName() + " " + result.getLastName(),
                    result.getChamber(),
                    result.getStateName(),
                    url);
            this.data.add(temp);
        }
    }

    private void initializeRecyclerView(View view) {
        adapter = new RVAdapter(data, legislators, view);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);
    }

    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.SunlightDataViewHolder> {

        List<Result> legislators = new ArrayList<>();
        List<LegislatorsDataAdapter> data;
        View view;

        RVAdapter(List<LegislatorsDataAdapter> data, List<Result> legislators, View view) {
            this.data = data;
            this.legislators = legislators;
            this.view = view;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public SunlightDataViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_legislators, viewGroup, false);
            SunlightDataViewHolder pvh = new SunlightDataViewHolder(v, new SunlightDataViewHolder.ISunlightDataViewHolderClicks() {
                public void onClickItem(View caller) {
                    AppCompatActivity host = (AppCompatActivity) view.getContext();
                    RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycler_view);
                    int itemPosition = rv.getChildAdapterPosition(caller);

                    Result legislator = legislators.get(itemPosition);
                    LegislatorsDataAdapter adapter = data.get(itemPosition);
                    LegislatorDetailsFragment detailsFragment = new LegislatorDetailsFragment();
                    detailsFragment.show(host.getSupportFragmentManager(), R.id.bottomsheet);
                    detailsFragment.sendData(legislator, adapter);
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(SunlightDataViewHolder sunlightDataViewHolder, int i) {
            sunlightDataViewHolder.mainTitle.setText(data.get(i).getMainTitle());
            sunlightDataViewHolder.mainDescription.setText(data.get(i).getMainDescription());

            Context context = sunlightDataViewHolder.personPhoto.getContext();
            Picasso.with(context)
                    .load(data.get(i).getPhotoURL())
                    .placeholder(R.drawable.progress_animation)
                    .fit().centerCrop()
                    .transform(new CircleTransform())
                    .into(sunlightDataViewHolder.personPhoto);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class SunlightDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            RelativeLayout relativeLayout;
            TextView mainTitle;
            TextView mainDescription;
            ImageView personPhoto;
            public ISunlightDataViewHolderClicks mListener;

            SunlightDataViewHolder(View itemView, ISunlightDataViewHolderClicks listener) {
                super(itemView);
                mListener = listener;
                relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
                relativeLayout.setOnClickListener(this);
                mainTitle = ButterKnife.findById(itemView, R.id.mainTitle);
                mainDescription = ButterKnife.findById(itemView, R.id.mainDescription);
                personPhoto = ButterKnife.findById(itemView, R.id.person_photo);
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
}