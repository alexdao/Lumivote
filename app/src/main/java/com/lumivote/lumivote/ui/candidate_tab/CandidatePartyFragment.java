package com.lumivote.lumivote.ui.candidate_tab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lumivote.lumivote.R;
import com.lumivote.lumivote.TinyDB;
import com.lumivote.lumivote.api.huffpost_responses.republican_primary_polls.Estimate;
import com.lumivote.lumivote.bus.BusProvider;
import com.lumivote.lumivote.bus.HuffPostDemocratPrimaryPollsEvent;
import com.lumivote.lumivote.bus.HuffPostRepublicanPrimaryPollsEvent;
import com.lumivote.lumivote.ui.DividerItemDecoration;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CandidatePartyFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private List<Person> democrats_persons;
    private List<Person> republican_persons;
    private List<Person> independent_persons;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    LinearLayoutManager llm;
    RVAdapter adapter;

    public static CandidatePartyFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        CandidatePartyFragment fragment = new CandidatePartyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_candidate_party, container, false);
        ButterKnife.bind(this, view);
        initializeRecyclerView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initializeRecyclerView() {
        initializeData();
        if (mPage == 1) {
            adapter = new RVAdapter(democrats_persons, mPage);
        } else if (mPage == 2) {
            adapter = new RVAdapter(republican_persons, mPage);
        } else {
            adapter = new RVAdapter(independent_persons, mPage);
        }
        recyclerView.setAdapter(adapter);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void initializeData() {
        String[] democrats = getResources().getStringArray(R.array.democrats_array);
        String[] democrats_desc = getResources().getStringArray(R.array.democrats_desc_array);
        String[] democrats_url = getResources().getStringArray(R.array.democrats_images_url);
        String[] republicans = getResources().getStringArray(R.array.republican_array);
        String[] republican_desc = getResources().getStringArray(R.array.republican_desc_array);
        String[] republican_url = getResources().getStringArray(R.array.republican_images_url);
        String[] independents = getResources().getStringArray(R.array.independents_array);
        String[] independent_desc = getResources().getStringArray(R.array.independents_desc_array);
        String[] independent_url = getResources().getStringArray(R.array.independents_images_url);


        democrats_persons = new ArrayList<>();
        for (int i = 0; i < democrats.length; i++) {
            democrats_persons.add(new Person(democrats[i], democrats_desc[i], democrats_url[i]));
        }
        republican_persons = new ArrayList<>();
        for (int i = 0; i < republicans.length; i++) {
            republican_persons.add(new Person(republicans[i], republican_desc[i], republican_url[i]));
        }
        independent_persons = new ArrayList<>();
        for (int i = 0; i < independents.length; i++) {
            independent_persons.add(new Person(independents[i], independent_desc[i], independent_url[i]));
        }
    }

    class Person {
        String name;
        String description;
        String photoURL;

        Person(String name, String description, String photoURL) {
            this.name = name;
            this.description = description;
            this.photoURL = photoURL;
        }
    }

    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

        List<Person> persons;
        int mPage;

        RVAdapter(List<Person> persons, int mPage) {
            this.persons = persons;
            this.mPage = mPage;
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }

        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            final View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_candidate_party, viewGroup, false);

            final PersonViewHolder pvh = new PersonViewHolder(v);
            pvh.setListener(new PersonViewHolder.IPersonViewHolderClicks() {
                public void onClickItem(View caller) {
                    Log.d("Clicked the candidate", "Success");
                }

                public void onClickStar(ImageView caller) {
                    Context context = v.getContext();
                    String candidate_name = persons.get(pvh.position).name;

                    TinyDB tinyDB = new TinyDB(context);
                    HashSet<String> starred_candidates = tinyDB.getSet(context.getString(R.string.starred_candidates_list));
                    if (starred_candidates.contains(candidate_name)) {
                        starred_candidates.remove(candidate_name);
                        Picasso.with(context)
                                .load(R.drawable.blue_star_outline)
                                .fit().centerCrop()
                                .into(pvh.star);
                    } else {
                        starred_candidates.add(candidate_name);
                        Picasso.with(context)
                                .load(R.drawable.blue_star_fill)
                                .fit().centerCrop()
                                .into(pvh.star);
                    }
                    tinyDB.putSet(context.getString(R.string.starred_candidates_list), starred_candidates);
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
            personViewHolder.personName.setText(persons.get(i).name);
            personViewHolder.personDesc.setText(persons.get(i).description);
            personViewHolder.position = personViewHolder.getAdapterPosition();

            CircleAngleAnimation animation = new CircleAngleAnimation(personViewHolder.circle, 240);
            animation.setDuration(1000);
            personViewHolder.circle.startAnimation(animation);

            Context context = personViewHolder.personPhoto.getContext();
            Picasso.with(context)
                    .load(persons.get(i).photoURL)
                    .placeholder(R.drawable.progress_animation)
                    .fit().centerCrop()
                    .transform(new CircleTransform())
                    .into(personViewHolder.personPhoto);

            TinyDB tinyDB = new TinyDB(context);
            HashSet<String> starred_candidates = tinyDB.getSet(context.getString(R.string.starred_candidates_list));
            if(starred_candidates.contains(personViewHolder.personName.getText())){
                Picasso.with(context)
                        .load(R.drawable.blue_star_fill)
                        .fit().centerCrop()
                        .into(personViewHolder.star);
            }
            else {
                Picasso.with(context)
                        .load(R.drawable.blue_star_outline)
                        .fit().centerCrop()
                        .into(personViewHolder.star);
            }
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            int position;
            RelativeLayout relativeLayout;
            Circle circle;
            ImageView personPhoto;
            TextView personName;
            TextView personDesc;
            ImageView star;
            public IPersonViewHolderClicks mListener;

            PersonViewHolder(View itemView) {
                super(itemView);
                relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
                circle = ButterKnife.findById(itemView, R.id.circle);
                personPhoto = ButterKnife.findById(itemView, R.id.person_photo);
                personName = ButterKnife.findById(itemView, R.id.person_name);
                personDesc = ButterKnife.findById(itemView, R.id.person_desc);
                star = ButterKnife.findById(itemView, R.id.candidate_star);
            }

            private void setListener(IPersonViewHolderClicks listener){
                mListener = listener;
                star.setOnClickListener(this);
                relativeLayout.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if(v instanceof ImageView){
                    mListener.onClickStar((ImageView) v);
                }
                else{
                    mListener.onClickItem(v);
                }
            }

            public interface IPersonViewHolderClicks {
                void onClickItem(View caller);
                void onClickStar(ImageView callerImage);
            }
        }
    }

    @Subscribe
    public void handleHuffPostDemocratPollsEvent(HuffPostDemocratPrimaryPollsEvent event) {
        String test = event.getDemocratPolls().get(0).getFirstName().toString();
        com.lumivote.lumivote.api.huffpost_responses.democrat_primary_polls.Estimate estimate = event.getDemocratPolls().get(0);
        double percent = estimate.getValue();
        Log.v(test + "'s Poll Percentage: ", ""+percent);
    }

    @Subscribe
    public void handleHuffPostRepublicanPollsEvent(HuffPostRepublicanPrimaryPollsEvent event) {
        String test = event.getRepublicanPolls().get(0).getFirstName().toString();
        Estimate estimate = event.getRepublicanPolls().get(0);
        double percent = estimate.getValue();
        Log.v(test + "'s Poll Percentage: ", ""+percent);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }
}
