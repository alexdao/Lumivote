package com.lumivote.lumivote.api;

import android.util.Log;

import com.lumivote.lumivote.api.huffpost_responses.democrat_primary_polls.DemocratPollResponse;
import com.lumivote.lumivote.api.huffpost_responses.republican_primary_polls.Estimate;
import com.lumivote.lumivote.api.huffpost_responses.republican_primary_polls.RepublicanPollResponse;
import com.lumivote.lumivote.bus.BusProvider;
import com.lumivote.lumivote.bus.HuffPostDemocratPrimaryPollsEvent;
import com.lumivote.lumivote.bus.HuffPostRepublicanPrimaryPollsEvent;
import com.squareup.otto.Bus;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by alex on 8/11/15.
 */
public class HuffPostPollRESTClient {

    private static final String API_URL = "http://elections.huffingtonpost.com/pollster/api";
    private static final HuffPostPollRESTClient restClient = new HuffPostPollRESTClient();

    private Bus eventBus;

    public List<Estimate> republicans;
    public DemocratPollResponse democrats;

    private HuffPostPollRESTClient() {

    }

    public static HuffPostPollRESTClient getInstance() {
        restClient.eventBus = BusProvider.getInstance();
        restClient.eventBus.register(restClient);
        return restClient;
    }

    public interface HuffPostService {
        @GET("/{method}")
        void fetchRepublicanPolls(
                @Path("method") String method,
                @Query("topic") String topic,
                Callback<List<RepublicanPollResponse>> republicanPollResponseCallback);

        @GET("/{method}")
        void fetchDemocratPolls(
                @Path("method") String method,
                @Query("topic") String topic,
                Callback<List<DemocratPollResponse>> democratPollResponseCallback);
    }

    public void fetchRepublicanPrimaryPolls() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        HuffPostService service = restAdapter.create(HuffPostService.class);
        service.fetchRepublicanPolls("charts", "2016-president-gop-primary",
                new Callback<List<RepublicanPollResponse>>() {
                    @Override
                    public void success(List<RepublicanPollResponse> republicanResponse, Response response) {
                        republicans = republicanResponse.get(0).getEstimates();
                        eventBus.post(new HuffPostRepublicanPrimaryPollsEvent(
                                HuffPostRepublicanPrimaryPollsEvent.Type.COMPLETED,
                                republicans));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Republican primary polls", error.getCause());
                    }
                });
    }

    public void fetchDemocratPrimaryPolls() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        HuffPostService service = restAdapter.create(HuffPostService.class);
        service.fetchDemocratPolls("charts", "2016-president-dem-primary",
                new Callback<List<DemocratPollResponse>>() {
                    @Override
                    public void success(List<DemocratPollResponse> democratPollResponse, Response response) {
                        democrats = democratPollResponse.get(0);
                        eventBus.post(new HuffPostDemocratPrimaryPollsEvent(
                                HuffPostDemocratPrimaryPollsEvent.Type.COMPLETED,
                                democrats
                        ));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Democrat primary polls", error.getCause());
                    }
                });
    }
}
