package com.lumivote.lumivote.ui.candidate_tab;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lumivote.lumivote.R;
import com.lumivote.lumivote.TinyDB;
import com.lumivote.lumivote.api.HuffPostPollRESTClient;
import com.lumivote.lumivote.api.huffpost_responses.republican_primary_polls.Estimate;
import com.lumivote.lumivote.bus.BusProvider;
import com.lumivote.lumivote.bus.HuffPostDemocratPrimaryPollsEvent;
import com.lumivote.lumivote.bus.HuffPostRepublicanPrimaryPollsEvent;
import com.lumivote.lumivote.ui.DividerItemDecoration;
import com.lumivote.lumivote.ui.detail_fragments.CandidateDetailsFragment;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CandidatePartyFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private List<Person> democrats_persons;
    private List<Person> republican_persons;

    private static HashMap<String, Float> democrat_polls = new HashMap<>();
    private static HashMap<String, Float> republican_polls = new HashMap<>();

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
        try{
            mPage = getArguments().getInt(ARG_PAGE);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_candidate_party, container, false);
        ButterKnife.bind(this, view);
        showTabLayout();
        fetchDataFromHuffPost();
        initializeRecyclerView(view);
        return view;
    }

    private void fetchDataFromHuffPost() {
        HuffPostPollRESTClient client = HuffPostPollRESTClient.getInstance();
        client.fetchDemocratPrimaryPolls();
        client.fetchRepublicanPrimaryPolls();
    }

    private void initializeRecyclerView(View view) {
        initializeData();
        if (mPage == 1) {
            adapter = new RVAdapter(democrats_persons, view, mPage);
        } else {
            adapter = new RVAdapter(republican_persons, view, mPage);
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


        democrats_persons = new ArrayList<>();
        for (int i = 0; i < democrats.length; i++) {
            democrats_persons.add(new Person(democrats[i], democrats_desc[i], democrats_url[i]));
        }
        republican_persons = new ArrayList<>();
        for (int i = 0; i < republicans.length; i++) {
            republican_persons.add(new Person(republicans[i], republican_desc[i], republican_url[i]));
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
        List<CandidateDataAdapter> adapters;
        int mPage;
        View view;

        RVAdapter(List<Person> persons, View view, int mPage) {
            this.persons = persons;
            this.view = view;
            this.mPage = mPage;

            adapters = new ArrayList<>();
            for(Person p: persons){
                adapters.add(new CandidateDataAdapter(p.name, p.photoURL, p.description));
            }
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }

        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            final View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_candidates, viewGroup, false);

            final PersonViewHolder pvh = new PersonViewHolder(v);
            pvh.setListener(new PersonViewHolder.IPersonViewHolderClicks() {
                public void onClickItem(View caller) {
                    AppCompatActivity host = (AppCompatActivity) view.getContext();
                    RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycler_view);
                    int itemPosition = rv.getChildAdapterPosition(caller);

                    CandidateDataAdapter adapter = adapters.get(itemPosition);
                    CandidateDetailsFragment detailsFragment = new CandidateDetailsFragment();
                    detailsFragment.show(host.getSupportFragmentManager(), R.id.bottomsheet);
                    detailsFragment.sendData(adapter);

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
            String name = persons.get(i).name;

            personViewHolder.personName.setText(name);
            personViewHolder.personDesc.setText(persons.get(i).description);
            personViewHolder.position = personViewHolder.getAdapterPosition();

            float pollAngle;
            if (democrat_polls.containsKey(name)) {
                float pollPercent = democrat_polls.get(name);
                pollAngle = 360 * (pollPercent / 100);
            } else if (republican_polls.containsKey(name)) {
                float pollPercent = republican_polls.get(name);
                pollAngle = 360 * (pollPercent / 100);
            } else {
                pollAngle = 0;
            }

            CircleAngleAnimation animation = new CircleAngleAnimation(personViewHolder.circle, pollAngle);
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
            if (starred_candidates.contains(personViewHolder.personName.getText())) {
                Picasso.with(context)
                        .load(R.drawable.blue_star_fill)
                        .fit().centerCrop()
                        .into(personViewHolder.star);
            } else {
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

            private void setListener(IPersonViewHolderClicks listener) {
                mListener = listener;
                star.setOnClickListener(this);
                relativeLayout.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if (v instanceof ImageView) {
                    mListener.onClickStar((ImageView) v);
                } else {
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
        List<com.lumivote.lumivote.api.huffpost_responses.democrat_primary_polls.Estimate> polls = event.getDemocratPolls();

        for (int i = 0; i < polls.size(); i++) {
            if (polls.get(i).getFirstName() == null) {
                break;
            }
            String full_name = polls.get(i).getFirstName().toString() + " " + polls.get(i).getLastName().toString();
            float percent = polls.get(i).getValue().floatValue();
            democrat_polls.put(full_name, percent);
        }
        adapter.notifyDataSetChanged();
    }

    @Subscribe
    public void handleHuffPostRepublicanPollsEvent(HuffPostRepublicanPrimaryPollsEvent event) {
        List<Estimate> polls = event.getRepublicanPolls();

        for (int i = 0; i < polls.size(); i++) {
            if (polls.get(i).getFirstName() == null) {
                break;
            }
            String full_name = polls.get(i).getFirstName().toString() + " " + polls.get(i).getLastName().toString();
            float percent = polls.get(i).getValue().floatValue();
            republican_polls.put(full_name, percent);
        }
        adapter.notifyDataSetChanged();
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

    private void showTabLayout() {
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.VISIBLE);
        ViewPager mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        mViewPager.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
