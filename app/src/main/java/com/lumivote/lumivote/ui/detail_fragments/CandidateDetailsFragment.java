package com.lumivote.lumivote.ui.detail_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.lumivote.lumivote.R;
import com.lumivote.lumivote.ui.candidate_tab.CandidateDataAdapter;
import com.lumivote.lumivote.ui.candidate_tab.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CandidateDetailsFragment extends BottomSheetFragment {

    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.photo)
    ImageView photo;
    @Bind(R.id.description)
    TextView description;

    CandidateDataAdapter adapter;

    public CandidateDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_candidate_details, container, false);
        ButterKnife.bind(this, v);
        setContent();
        return v;
    }

    public void sendData(CandidateDataAdapter adapter) {
        this.adapter = adapter;
    }

    private void setContent() {
        name.setText(adapter.getName());
        description.setText(adapter.getDescription());

        Picasso.with(getActivity())
                .load(adapter.getImageURL())
                .placeholder(R.drawable.progress_animation)
                .fit().centerCrop()
                .transform(new CircleTransform())
                .into(photo);
    }
}
