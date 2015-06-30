package com.lumivote.lumivote.api.sunlight_responses.upcoming_bills;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Result {

    @SerializedName("legislative_day")
    @Expose
    private String legislativeDay;
    @Expose
    private String range;
    @Expose
    private String chamber;
    @SerializedName("bill_id")
    @Expose
    private String billId;
    @SerializedName("scheduled_at")
    @Expose
    private String scheduledAt;
    @Expose
    private Integer congress;
    @SerializedName("source_type")
    @Expose
    private String sourceType;
    @Expose
    private String url;
    @SerializedName("bill_url")
    @Expose
    private String billUrl;
    @Expose
    private String description;
    @Expose
    private String consideration;
    @SerializedName("floor_id")
    @Expose
    private String floorId;
    @Expose
    private String context;

    /**
     * 
     * @return
     *     The legislativeDay
     */
    public String getLegislativeDay() {
        return legislativeDay;
    }

    /**
     * 
     * @param legislativeDay
     *     The legislative_day
     */
    public void setLegislativeDay(String legislativeDay) {
        this.legislativeDay = legislativeDay;
    }

    /**
     * 
     * @return
     *     The range
     */
    public String getRange() {
        return range;
    }

    /**
     * 
     * @param range
     *     The range
     */
    public void setRange(String range) {
        this.range = range;
    }

    /**
     * 
     * @return
     *     The chamber
     */
    public String getChamber() {
        return chamber;
    }

    /**
     * 
     * @param chamber
     *     The chamber
     */
    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    /**
     * 
     * @return
     *     The billId
     */
    public String getBillId() {
        return billId;
    }

    /**
     * 
     * @param billId
     *     The bill_id
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * 
     * @return
     *     The scheduledAt
     */
    public String getScheduledAt() {
        return scheduledAt;
    }

    /**
     * 
     * @param scheduledAt
     *     The scheduled_at
     */
    public void setScheduledAt(String scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    /**
     * 
     * @return
     *     The congress
     */
    public Integer getCongress() {
        return congress;
    }

    /**
     * 
     * @param congress
     *     The congress
     */
    public void setCongress(Integer congress) {
        this.congress = congress;
    }

    /**
     * 
     * @return
     *     The sourceType
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * 
     * @param sourceType
     *     The source_type
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
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
     *     The billUrl
     */
    public String getBillUrl() {
        return billUrl;
    }

    /**
     * 
     * @param billUrl
     *     The bill_url
     */
    public void setBillUrl(String billUrl) {
        this.billUrl = billUrl;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The consideration
     */
    public String getConsideration() {
        return consideration;
    }

    /**
     * 
     * @param consideration
     *     The consideration
     */
    public void setConsideration(String consideration) {
        this.consideration = consideration;
    }

    /**
     * 
     * @return
     *     The floorId
     */
    public String getFloorId() {
        return floorId;
    }

    /**
     * 
     * @param floorId
     *     The floor_id
     */
    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    /**
     * 
     * @return
     *     The context
     */
    public String getContext() {
        return context;
    }

    /**
     * 
     * @param context
     *     The context
     */
    public void setContext(String context) {
        this.context = context;
    }

}
