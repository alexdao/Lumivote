package com.lumivote.lumivote.api.sunlight_responses;

import android.util.Log;

import com.lumivote.lumivote.bus.BusProvider;
import com.squareup.otto.Bus;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by alex on 8/21/15.
 */
public class LumivoteRESTClient {

    private static final String API_URL = "http://lumivote.com/api/events";
    private static final LumivoteRESTClient restClient = new LumivoteRESTClient();

    private Bus eventBus;

    public List<TimelineResult> timeline_list;

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

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Legislators", error.getCause());
                    }
                });
    }
}
