package com.lumivote.lumivote.ui.timeline_tab;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lumivote.lumivote.R;
import com.lumivote.lumivote.api.lumivote_responses.timeline.Timeline;
import com.lumivote.lumivote.ui.detail_fragments.TimelineDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by alex on 12/7/15.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TimelineDataViewHolder> {

    private List<TimelineDataAdapter> timelineData;
    private List<Timeline> rawTimelineData;
    private View view;

    RVAdapter(List<TimelineDataAdapter> timelineData, List<Timeline> rawTimelineData, View view) {
        this.timelineData = timelineData;
        this.rawTimelineData = rawTimelineData;
        this.view = view;
    }

    public void clear() {
        timelineData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return timelineData.size();
    }

    @Override
    public RVAdapter.TimelineDataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_timeline, viewGroup, false);

        TimelineDataViewHolder pvh = new TimelineDataViewHolder(v, new TimelineDataViewHolder.ISunlightDataViewHolderClicks() {
            public void onClickItem(View caller) {
                AppCompatActivity host = (AppCompatActivity) view.getContext();
                RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycler_view);
                int itemPosition = rv.getChildAdapterPosition(caller);
                Log.d("what", timelineData.size() + " and raw " + rawTimelineData.size() + " ");
                Timeline data = rawTimelineData.get(itemPosition);
                TimelineDetailsFragment detailsFragment = new TimelineDetailsFragment();
                detailsFragment.sendData(data);
                detailsFragment.show(host.getSupportFragmentManager(), R.id.bottomsheet);
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(TimelineDataViewHolder timelineDataViewHolder, int i) {
        timelineDataViewHolder.title.setText(timelineData.get(i).getTitle());
        timelineDataViewHolder.description.setText(timelineData.get(i).getDescription());

        int logoID;
        String party = timelineData.get(i).getPartyType();
        if(party.equals("Republican")){
            logoID = R.drawable.republican_logo;
        }
        else if(party.equals("Democratic")){
            logoID = R.drawable.democratic_logo;
        }
        else if(party.equals("All")){
            logoID = R.drawable.us_flag;
        }
        else if(party.equals("Libertarian")){
            logoID = R.drawable.libertarian_logo;
        }
        else{
            logoID = R.drawable.blue_star_fill;
        }

        Context context = timelineDataViewHolder.logo.getContext();
        Picasso.with(context)
                .load(logoID)
                .fit()
                .into(timelineDataViewHolder.logo);
    }

    public static class TimelineDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout relativeLayout;
        ImageView logo;
        TextView title;
        TextView description;
        public ISunlightDataViewHolderClicks mListener;

        TimelineDataViewHolder(View itemView, ISunlightDataViewHolderClicks listener) {
            super(itemView);
            mListener = listener;
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
            relativeLayout.setOnClickListener(this);
            logo = ButterKnife.findById(itemView, R.id.logo_photo);
            title = ButterKnife.findById(itemView, R.id.title);
            description = ButterKnife.findById(itemView, R.id.description);
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