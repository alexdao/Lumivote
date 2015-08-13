package com.lumivote.lumivote.api.huffpost_responses.democrat_primary_polls;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class DemocratPollResponse {

    @Expose
    private String title;
    @Expose
    private String slug;
    @Expose
    private String topic;
    @Expose
    private String state;
    @SerializedName("short_title")
    @Expose
    private String shortTitle;
    @SerializedName("election_date")
    @Expose
    private String electionDate;
    @SerializedName("poll_count")
    @Expose
    private Integer pollCount;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @Expose
    private String url;
    @Expose
    private List<Object> estimates = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * 
     * @param slug
     *     The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * 
     * @return
     *     The topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 
     * @param topic
     *     The topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * 
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The shortTitle
     */
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * 
     * @param shortTitle
     *     The short_title
     */
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    /**
     * 
     * @return
     *     The electionDate
     */
    public String getElectionDate() {
        return electionDate;
    }

    /**
     * 
     * @param electionDate
     *     The election_date
     */
    public void setElectionDate(String electionDate) {
        this.electionDate = electionDate;
    }

    /**
     * 
     * @return
     *     The pollCount
     */
    public Integer getPollCount() {
        return pollCount;
    }

    /**
     * 
     * @param pollCount
     *     The poll_count
     */
    public void setPollCount(Integer pollCount) {
        this.pollCount = pollCount;
    }

    /**
     * 
     * @return
     *     The lastUpdated
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * 
     * @param lastUpdated
     *     The last_updated
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The estimates
     */
    public List<Object> getEstimates() {
        return estimates;
    }

    /**
     * 
     * @param estimates
     *     The estimates
     */
    public void setEstimates(List<Object> estimates) {
        this.estimates = estimates;
    }

}
