package com.lumivote.lumivote.workhorse;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lumivote.lumivote.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alex on 7/24/15.
 */
public class SunlightRecyclerView {

    private List<Data> data;

    View v;
    Fragment parentFragment;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    LinearLayoutManager llm;
    RVAdapter adapter;

    public SunlightRecyclerView(){

    }

    public SunlightRecyclerView(Fragment fragment, View v) {
        this.v = v;
        this.parentFragment = fragment;
        ButterKnife.bind(fragment, v);
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {

        adapter = new RVAdapter(data);

        recyclerView.setAdapter(adapter);
        llm = new LinearLayoutManager(parentFragment.getActivity());
        recyclerView.setLayoutManager(llm);
    }

    class Data{
        String mainTitle;
        String mainDescription;
        String leftTitle;
        String leftDescription;

        Data(String mainTitle, String mainDescription, String leftTitle, String leftDescription){
            this.mainTitle = mainTitle;
            this.mainDescription = mainDescription;
            this.leftTitle = leftTitle;
            this.leftDescription = leftDescription;
        }
    }

    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

        List<Data> data;

        RVAdapter(List<Data> data) {
            this.data = data;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_candidate_party, viewGroup, false);
            PersonViewHolder pvh = new PersonViewHolder(v, new RVAdapter.PersonViewHolder.IPersonViewHolderClicks() {
                public void onClickItem(View caller) {
                    Log.d("Hello", "test");
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
            personViewHolder.mainTitle.setText(data.get(i).mainTitle);
            personViewHolder.mainDescription.setText(data.get(i).mainDescription);
            personViewHolder.leftTitle.setText(data.get(i).leftTitle);
            personViewHolder.leftDescription.setText(data.get(i).leftDescription);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            RelativeLayout relativeLayout;
            TextView leftTitle;
            TextView leftDescription;
            TextView mainTitle;
            TextView mainDescription;
            public IPersonViewHolderClicks mListener;

            PersonViewHolder(View itemView, IPersonViewHolderClicks listener) {
                super(itemView);
                mListener = listener;
                relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
                relativeLayout.setOnClickListener(this);
                leftTitle = ButterKnife.findById(itemView, R.id.leftTitle);
                leftDescription = ButterKnife.findById(itemView, R.id.leftDescription);
                mainTitle = ButterKnife.findById(itemView, R.id.mainTitle);
                mainDescription = ButterKnife.findById(itemView, R.id.mainDescription);

            }

            @Override
            public void onClick(View v) {
                mListener.onClickItem(v);
            }

            public interface IPersonViewHolderClicks {
                void onClickItem(View caller);
            }
        }
    }


}
