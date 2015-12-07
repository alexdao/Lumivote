package com.lumivote.lumivote.ui.detail_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.lumivote.lumivote.R;
import com.lumivote.lumivote.api.sunlight_responses.bills.Result;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BillDetailsFragment extends BottomSheetFragment {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.sponsor)
    TextView sponsor;
    @Bind(R.id.chamber)
    TextView chamber;

    Result bill;

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

    public void sendData(Result bill) {
        this.bill = bill;
    }

    private void setContent() {
        title.setText("Official title: " + bill.getOfficialTitle());
        date.setText("\nBill introduced on: " + bill.getIntroducedOn());
        sponsor.setText("Sponsor: " + bill.getSponsor().getFirstName() + " " + bill.getSponsor().getLastName());
        chamber.setText("Chamber: " + capitalizeFirstLetter(bill.getChamber()));
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
