package com.lumivote.lumivote.api.sunlight_responses.votes;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class VotesResponse {

    @Expose
    private List<Result> results = new ArrayList<Result>();
    @Expose
    private Integer count;
    @Expose
    private Page page;

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * @return The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The page
     */
    public Page getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(Page page) {
        this.page = page;
    }

}
