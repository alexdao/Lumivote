package com.lumivote.lumivote.ui.detail_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.lumivote.lumivote.R;
import com.lumivote.lumivote.api.sunlight_responses.votes.Result;
import com.lumivote.lumivote.ui.votes_tab.VotesDataAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VoteDetailsFragment extends BottomSheetFragment {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.chamber)
    TextView chamber;
    @Bind(R.id.billID)
    TextView billID;

    Result vote;
    VotesDataAdapter adapter;

    public VoteDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vote_details, container, false);
        ButterKnife.bind(this, v);
        setContent();
        return v;
    }

    private void setContent() {
        title.setText(adapter.getLeftTitle());
        date.setText("\nVoted on: " + adapter.getMainTitle());
        description.setText(adapter.getLeftDescription());
        chamber.setText("Chamber: " + capitalizeFirstLetter(vote.getChamber()));
        billID.setText("Bill ID: " + vote.getBillId());
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public void sendData(Result vote, VotesDataAdapter adapter) {
        this.vote = vote;
        this.adapter = adapter;
    }
}
