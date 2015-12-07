package com.lumivote.lumivote.ui.detail_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.lumivote.lumivote.R;
import com.lumivote.lumivote.ui.bills_tab.BillsDataAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BillDetailsFragment extends BottomSheetFragment {

    @Bind(R.id.title)
    TextView textView;

    BillsDataAdapter adapter;

    public BillDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bill_details, container, false);
        ButterKnife.bind(this, v);
        setContent();
        return v;
    }

    public void sendData(BillsDataAdapter adapter){
        this.adapter = adapter;
    }

    private void setContent(){
        textView.setText(adapter.getMainTitle());
    }
}
