package com.lumivote.lumivote.api;

import android.util.Log;

import com.lumivote.lumivote.api.lumivote_responses.candidates.Candidate;
import com.lumivote.lumivote.api.lumivote_responses.candidates.CandidateResponse;
import com.lumivote.lumivote.api.lumivote_responses.timeline.Timeline;
import com.lumivote.lumivote.api.lumivote_responses.timeline.TimelineResponse;
import com.lumivote.lumivote.bus.BusProvider;
import com.lumivote.lumivote.bus.LumivoteTimelineEvent;
import com.squareup.otto.Bus;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by alex on 8/21/15.
 */
public class LumivoteRESTClient {

    private static final String API_URL = "http://lumivote.com/api";
    private static final LumivoteRESTClient restClient = new LumivoteRESTClient();

    private Bus eventBus;

    public List<Timeline> timeline_list;
    public List<Candidate> candidate_list;

    private LumivoteRESTClient() {

    }

    public static LumivoteRESTClient getInstance() {
        restClient.eventBus = BusProvider.getInstance();
        restClient.eventBus.register(restClient);
        return restClient;
    }

    public interface LumivoteService {
        @GET("/{method}")
        void listTimelineEvents(
                @Path("method") String method,
                Callback<TimelineResponse> legislatorList);

        @GET("/{method}")
        void listCandidates(
                @Path("method") String method,
                Callback<CandidateResponse> candidateList);
    }

    public void fetchTimelineEvents() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        LumivoteService service = restAdapter.create(LumivoteService.class);
        service.listTimelineEvents("events",
                new Callback<TimelineResponse>() {
                    @Override
                    public void success(TimelineResponse timelineResponse, Response response) {
                        timeline_list = timelineResponse.getTimeline();
                        eventBus.post(new LumivoteTimelineEvent(LumivoteTimelineEvent.Type.COMPLETED, timeline_list));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Timeline", error.getCause());
                    }
                });
    }

    public void fetchCandidateList() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        LumivoteService service = restAdapter.create(LumivoteService.class);
        service.listCandidates("candidates",
                new Callback<CandidateResponse>() {
                    @Override
                    public void success(CandidateResponse candidateResponse, Response response) {
                        candidate_list = candidateResponse.getCandidates();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Candidates", error.getCause());
                    }
                });
    }
}
