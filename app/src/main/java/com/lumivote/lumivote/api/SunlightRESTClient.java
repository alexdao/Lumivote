package com.lumivote.lumivote.api;

import android.util.Log;


import com.lumivote.lumivote.api.sunlight_responses.bills.BillsResponse;
import com.lumivote.lumivote.api.sunlight_responses.legislators.LegislatorsResponse;
import com.lumivote.lumivote.api.sunlight_responses.legislators.Result;
import com.lumivote.lumivote.api.sunlight_responses.upcoming_bills.UpcomingBillsResponse;
import com.lumivote.lumivote.api.sunlight_responses.votes.VotesResponse;

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

    private static final String API_URL= "https://congress.api.sunlightfoundation.com";

    public List<Result> legislators_list;
    public List<com.lumivote.lumivote.api.sunlight_responses.bills.Result> bills_list;
    public List<com.lumivote.lumivote.api.sunlight_responses.upcoming_bills.Result> upcoming_bills_list;
    public List<com.lumivote.lumivote.api.sunlight_responses.votes.Result> votes_list;

    public SunlightRESTClient(){

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
                        Integer count = 0;
                        legislators_list = legislatorsResponse.getResults();
                        for (Result person : legislators_list) {
                            Log.i(count.toString(), person.getFirstName());
                            count++;
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Legislators", error.getCause());
                    }
                });
    }

    public void fetchBills(int pageNumber){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        SunlightService service = restAdapter.create(SunlightService.class);
        service.listBills("bills", pageNumber,
                new Callback<BillsResponse>() {
                    @Override
                    public void success(BillsResponse bills, Response response) {
                        Integer count = 0;
                        List<com.lumivote.lumivote.api.sunlight_responses.bills.Result> billsResults = bills.getResults();
                        bills_list = billsResults;
                        for (com.lumivote.lumivote.api.sunlight_responses.bills.Result bill : billsResults) {
                            if(bill.getBillId() != null){
                                Log.i(count.toString(), bill.getBillId());
                            }
                            else{
                                Log.i(count.toString(), "bill ID was null");
                            }
                            count++;
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Bills", error.getCause());
                    }
                });
    }

    public void fetchUpcomingBills(int pageNumber){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        SunlightService service = restAdapter.create(SunlightService.class);
        service.listUpcomingBills("upcoming_bills", pageNumber,
                new Callback<UpcomingBillsResponse>() {
                    @Override
                    public void success(UpcomingBillsResponse bills, Response response) {
                        Integer count = 0;
                        List<com.lumivote.lumivote.api.sunlight_responses.upcoming_bills.Result> billsResults = bills.getResults();
                        upcoming_bills_list = billsResults;
                        for (com.lumivote.lumivote.api.sunlight_responses.upcoming_bills.Result bill : billsResults) {
                            if(bill.getBillUrl() != null){
                                Log.i(count.toString(), bill.getBillUrl());
                            }
                            else{
                                Log.i(count.toString(), "bill URL was null");
                            }
                            count++;
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Upcoming Bills", error.getCause());
                    }
                });
    }

    public void fetchVotes(int pageNumber){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();

        SunlightService service = restAdapter.create(SunlightService.class);
        service.listVotes("votes", pageNumber,
                new Callback<VotesResponse>() {
                    @Override
                    public void success(VotesResponse votes, Response response) {
                        List<com.lumivote.lumivote.api.sunlight_responses.votes.Result> votesResults = votes.getResults();
                        votes_list = votesResults;
                        Log.e("sunlight rest client: ", ""+votes_list.size());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Connection failure: ", "Votes", error.getCause());
                    }
                });
    }
}