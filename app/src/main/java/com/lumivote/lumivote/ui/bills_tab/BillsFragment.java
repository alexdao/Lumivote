package com.lumivote.lumivote.ui.bills_tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lumivote.lumivote.R;


/**
 * Created by Alex Dao on May 24, 2015.
 */
public class BillsFragment extends Fragment {

    public static final String TAG = "bills";

    public BillsFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bills, container, false);
    }


}
