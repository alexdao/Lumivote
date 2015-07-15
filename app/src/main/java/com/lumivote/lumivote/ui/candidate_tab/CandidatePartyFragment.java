package com.lumivote.lumivote.ui.candidate_tab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lumivote.lumivote.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_base);
        if(mPage == 1){
            linearLayout.setBackgroundColor(getResources().getColor(R.color.democratColor));
        }
        if(mPage == 2){
            linearLayout.setBackgroundColor(getResources().getColor(R.color.republicanColor));
        }
        if(mPage == 3){
            linearLayout.setBackgroundColor(getResources().getColor(R.color.independentColor));
        }
        ButterKnife.bind(this, view);
        initalizeRecyclerView();
        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initalizeRecyclerView(){
        initializeData();
        if(mPage == 1){
            adapter = new RVAdapter(democrats_persons, mPage);
        }
        else if(mPage == 2)
        {
            adapter = new RVAdapter(republican_persons, mPage);
        }
        else{
            adapter = new RVAdapter(independent_persons, mPage);
        }
        recyclerView.setAdapter(adapter);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
    }

    private void initializeData(){
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
        for(int i=0; i<democrats.length; i++){
            democrats_persons.add(new Person(democrats[i], democrats_desc[i], democrats_url[i]));
        }
        republican_persons = new ArrayList<>();
        for(int i=0; i<republicans.length; i++){
            republican_persons.add(new Person(republicans[i], republican_desc[i], republican_url[i]));
        }
        independent_persons = new ArrayList<>();
        for(int i=0; i<independents.length; i++){
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

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

        List<Person> persons;
        int mPage;

        RVAdapter(List<Person> persons, int mPage){
            this.persons = persons;
            this.mPage = mPage;
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }

        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_candidate_party, viewGroup, false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
            personViewHolder.personName.setText(persons.get(i).name);
            personViewHolder.personDesc.setText(persons.get(i).description);

            Context context = personViewHolder.personPhoto.getContext();
            Picasso.with(context)
                    .load(persons.get(i).photoURL)
                    .fit().centerCrop()
                    .into(personViewHolder.personPhoto);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public class PersonViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView personName;
            TextView personDesc;
            ImageView personPhoto;

            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.card_view);
                personName = (TextView)itemView.findViewById(R.id.person_name);
                personDesc = (TextView)itemView.findViewById(R.id.person_desc);
                personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            }
        }
    }
}
