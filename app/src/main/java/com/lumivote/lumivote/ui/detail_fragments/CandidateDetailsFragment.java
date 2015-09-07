package com.lumivote.lumivote.ui.detail_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.lumivote.lumivote.R;

public class CandidateDetailsFragment extends Fragment {

    public CandidateDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_candidate_details, container, false);

        return v;
    }
}
