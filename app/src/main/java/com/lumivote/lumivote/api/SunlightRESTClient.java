package com.lumivote.lumivote.api;

import android.util.Log;

import com.lumivote.lumivote.api.sunlight_responses.bills.BillsResponse;
import com.lumivote.lumivote.api.sunlight_responses.legislators.LegislatorsResponse;
import com.lumivote.lumivote.api.sunlight_responses.legislators.Result;
import com.lumivote.lumivote.api.sunlight_responses.upcoming_bills.UpcomingBillsResponse;
import com.lumivote.lumivote.api.sunlight_responses.votes.VotesResponse;
import com.lumivote.lumivote.bus.BusProvider;
import com.lumivote.lumivote.bus.SunlightBillsEvent;
import com.lumivote.lumivote.bus.SunlightLegislatorsEvent;
import com.lumivote.lumivote.bus.SunlightUpcomingBillsEvent;
import com.lumivote.lumivote.bus.SunlightVotesEvent;
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
 * Created by alex on 6/21/15.
 */
public class SunlightRESTClient {

    private static final String API_URL = "https://congress.api.sunlightfoundation.com";
    private static final SunlightRESTClient restClient = new SunlightRESTClient();

    private Bus eventBus;

    public List<Result> legislators_list;
    public List<com.lumivote.lumivote.api.sunlight_responses.bills.Result> bills_list;
    public List<com.lumivote.lumivote.api.sunlight_responses.upcoming_bills.Result> upcoming_bills_list;
    public List<com.lumivote.lumivote.api.sunlight_responses.votes.Result> votes_list;

    private SunlightRESTClient() {

    }

    public static SunlightRESTClient getInstance() {
        restClient.eventBus = BusProvider.getInstance();
        restClient.eventBus.register(restClient);
        return restClient;
    }

    //TODO: Change objects in list to a generic
    public interface SunlightService {
        @GET("/{method}?apikey=fb0f09c0b22b4b40aa81ad3933a9b090")
        void listLegislators(
                @Path("method") String method,
                @Query("page") int page_number,
                Callback<LegislatorsResponse> legislatorList);

        @GET("/{method}?apikey=fb0f09c0b22b4b40aa81ad3933a9b090")
        void listBills(
                @Path("method") String method,
                @Query("page") int page_number,
                Callback<BillsResponse> billsList);

        @GET("/{method}?apikey=fb0f09c0b22b4b40aa81ad3933a9b090")
        void listUpcomingBills(
                @Path("method") String method,
                @Query("page") int page_number,
                Callback<UpcomingBillsResponse> upcomingBillsList);

        @GET("/{method}?apikey=fb0f09c0b22b4b40aa81ad3933a9b090")
        void listVotes(
                @Path("method") String method,
                @Query("page") int page_number,
                Callback<VotesResponse> votesList);
    }

    public void fetchLegislators(int pageNumber) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        SunlightService service = restAdapter.create(SunlightService.class);
        service.listLegislators("legislators", pageNumber,
                new Callback<LegislatorsResponse>() {
                    @Override
                    public void success(LegislatorsResponse legislatorsResponse, Response response) {
                        legislators_list = legislatorsResponse.getResults();
                        eventBus.post(new SunlightLegislatorsEvent(SunlightLegislatorsEvent.Type.COMPLETED, legislators_list));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Legislators", error.getCause());
                    }
                });
    }

    public void fetchBills(int pageNumber) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        SunlightService service = restAdapter.create(SunlightService.class);
        service.listBills("bills", pageNumber,
                new Callback<BillsResponse>() {
                    @Override
                    public void success(BillsResponse bills, Response response) {
                        bills_list = bills.getResults();
                        eventBus.post(new SunlightBillsEvent(SunlightBillsEvent.Type.COMPLETED, bills_list));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Bills", error.getCause());
                    }
                });
    }

    public void fetchUpcomingBills(int pageNumber) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        SunlightService service = restAdapter.create(SunlightService.class);
        service.listUpcomingBills("upcoming_bills", pageNumber,
                new Callback<UpcomingBillsResponse>() {
                    @Override
                    public void success(UpcomingBillsResponse bills, Response response) {
                        upcoming_bills_list = bills.getResults();
                        eventBus.post(new SunlightUpcomingBillsEvent(SunlightUpcomingBillsEvent.Type.COMPLETED, upcoming_bills_list));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Upcoming Bills", error.getCause());
                    }
                });
    }

    public void fetchVotes(int pageNumber) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        SunlightService service = restAdapter.create(SunlightService.class);
        service.listVotes("votes", pageNumber,
                new Callback<VotesResponse>() {
                    @Override
                    public void success(VotesResponse votes, Response response) {
                        votes_list = votes.getResults();
                        eventBus.post(new SunlightVotesEvent(SunlightVotesEvent.Type.COMPLETED, votes_list));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Votes", error.getCause());
                    }
                });
    }

}