package com.lumivote.lumivote.api.sunlight_responses.votes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Page {

    @Expose
    private Integer count;
    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @Expose
    private Integer page;

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
     * @return The perPage
     */
    public Integer getPerPage() {
        return perPage;
    }

    /**
     * @param perPage The per_page
     */
    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    /**
     * @return The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

}
