package com.lumivote.lumivote.ui.detail_fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.lumivote.lumivote.R;
import com.lumivote.lumivote.api.lumivote_responses.timeline.Timeline;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alex on 12/7/15.
 */
public class TimelineDetailsFragment extends BottomSheetFragment {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.location)
    TextView location;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.logo_photo)
    ImageView logo;

    Timeline data;

    public TimelineDetailsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timeline_details, container, false);
        ButterKnife.bind(this, v);
        setContent();
        return v;
    }

    public void sendData(Timeline data) {
        this.data = data;
    }

    private void setContent() {
        title.setText(data.getName());
        date.setText("\n" + data.getDate());
        location.setText(data.getCity());
        description.setText("\n" + data.getDescription());

        int logoID;
        String party = data.getParty();
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

        Picasso.with(getActivity())
                .load(logoID)
                .fit()
                .into(logo);
    }
}
