package com.lumivote.lumivote.ui.detail_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.lumivote.lumivote.R;
import com.lumivote.lumivote.api.sunlight_responses.legislators.Result;
import com.lumivote.lumivote.ui.candidate_tab.CircleTransform;
import com.lumivote.lumivote.ui.legislators_tab.LegislatorsDataAdapter;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LegislatorDetailsFragment extends BottomSheetFragment {

    @Bind(R.id.photo)
    ImageView photo;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.termStart)
    TextView termStart;
    @Bind(R.id.termEnd)
    TextView termEnd;
    @Bind(R.id.chamber)
    TextView chamber;
    @Bind(R.id.state)
    TextView state;
    @Bind(R.id.party)
    TextView party;
    @Bind(R.id.title)
    TextView title;

    Result legislator;
    LegislatorsDataAdapter adapter;

    public LegislatorDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_legislator_details, container, false);
        ButterKnife.bind(this, v);
        setContent();
        return v;
    }

    private void setContent() {
        name.setText(legislator.getFirstName() + " " + legislator.getLastName());
        termStart.setText("\nTerm Start: " + legislator.getTermStart());
        termEnd.setText("Term End: " + legislator.getTermEnd());
        chamber.setText("Chamber: " + legislator.getChamber());
        state.setText("State: " + legislator.getStateName());
        party.setText("Party: " + legislator.getParty());
        title.setText("Title: " + legislator.getTitle());

        Picasso.with(getActivity())
                .load(adapter.getPhotoURL())
                .placeholder(R.drawable.progress_animation)
                .fit().centerCrop()
                .transform(new CircleTransform())
                .into(photo);
    }

    public void sendData(Result legislator, LegislatorsDataAdapter adapter) {
        this.legislator = legislator;
        this.adapter = adapter;
    }
}
