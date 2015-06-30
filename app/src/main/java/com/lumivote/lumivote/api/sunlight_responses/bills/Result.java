
package com.lumivote.lumivote.api.sunlight_responses.bills;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Result {

    @SerializedName("bill_id")
    @Expose
    private String billId;
    @SerializedName("bill_type")
    @Expose
    private String billType;
    @Expose
    private String chamber;
    @SerializedName("committee_ids")
    @Expose
    private List<String> committeeIds = new ArrayList<String>();
    @Expose
    private Integer congress;
    @SerializedName("cosponsors_count")
    @Expose
    private Integer cosponsorsCount;
    @SerializedName("enacted_as")
    @Expose
    private Object enactedAs;
    @Expose
    private History history;
    @SerializedName("introduced_on")
    @Expose
    private String introducedOn;
    @SerializedName("last_action_at")
    @Expose
    private String lastActionAt;
    @SerializedName("last_version_on")
    @Expose
    private String lastVersionOn;
    @SerializedName("last_vote_at")
    @Expose
    private Object lastVoteAt;
    @Expose
    private Integer number;
    @SerializedName("official_title")
    @Expose
    private String officialTitle;
    @SerializedName("popular_title")
    @Expose
    private Object popularTitle;
    @SerializedName("related_bill_ids")
    @Expose
    private List<Object> relatedBillIds = new ArrayList<Object>();
    @SerializedName("short_title")
    @Expose
    private Object shortTitle;
    @Expose
    private Sponsor sponsor;
    @SerializedName("sponsor_id")
    @Expose
    private String sponsorId;
    @Expose
    private Urls urls;
    @SerializedName("withdrawn_cosponsors_count")
    @Expose
    private Integer withdrawnCosponsorsCount;
    @SerializedName("last_version")
    @Expose
    private LastVersion lastVersion;

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
     *     The billType
     */
    public String getBillType() {
        return billType;
    }

    /**
     * 
     * @param billType
     *     The bill_type
     */
    public void setBillType(String billType) {
        this.billType = billType;
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
     *     The committeeIds
     */
    public List<String> getCommitteeIds() {
        return committeeIds;
    }

    /**
     * 
     * @param committeeIds
     *     The committee_ids
     */
    public void setCommitteeIds(List<String> committeeIds) {
        this.committeeIds = committeeIds;
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
     *     The cosponsorsCount
     */
    public Integer getCosponsorsCount() {
        return cosponsorsCount;
    }

    /**
     * 
     * @param cosponsorsCount
     *     The cosponsors_count
     */
    public void setCosponsorsCount(Integer cosponsorsCount) {
        this.cosponsorsCount = cosponsorsCount;
    }

    /**
     * 
     * @return
     *     The enactedAs
     */
    public Object getEnactedAs() {
        return enactedAs;
    }

    /**
     * 
     * @param enactedAs
     *     The enacted_as
     */
    public void setEnactedAs(Object enactedAs) {
        this.enactedAs = enactedAs;
    }

    /**
     * 
     * @return
     *     The history
     */
    public History getHistory() {
        return history;
    }

    /**
     * 
     * @param history
     *     The history
     */
    public void setHistory(History history) {
        this.history = history;
    }

    /**
     * 
     * @return
     *     The introducedOn
     */
    public String getIntroducedOn() {
        return introducedOn;
    }

    /**
     * 
     * @param introducedOn
     *     The introduced_on
     */
    public void setIntroducedOn(String introducedOn) {
        this.introducedOn = introducedOn;
    }

    /**
     * 
     * @return
     *     The lastActionAt
     */
    public String getLastActionAt() {
        return lastActionAt;
    }

    /**
     * 
     * @param lastActionAt
     *     The last_action_at
     */
    public void setLastActionAt(String lastActionAt) {
        this.lastActionAt = lastActionAt;
    }

    /**
     * 
     * @return
     *     The lastVersionOn
     */
    public String getLastVersionOn() {
        return lastVersionOn;
    }

    /**
     * 
     * @param lastVersionOn
     *     The last_version_on
     */
    public void setLastVersionOn(String lastVersionOn) {
        this.lastVersionOn = lastVersionOn;
    }

    /**
     * 
     * @return
     *     The lastVoteAt
     */
    public Object getLastVoteAt() {
        return lastVoteAt;
    }

    /**
     * 
     * @param lastVoteAt
     *     The last_vote_at
     */
    public void setLastVoteAt(Object lastVoteAt) {
        this.lastVoteAt = lastVoteAt;
    }

    /**
     * 
     * @return
     *     The number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 
     * @param number
     *     The number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 
     * @return
     *     The officialTitle
     */
    public String getOfficialTitle() {
        return officialTitle;
    }

    /**
     * 
     * @param officialTitle
     *     The official_title
     */
    public void setOfficialTitle(String officialTitle) {
        this.officialTitle = officialTitle;
    }

    /**
     * 
     * @return
     *     The popularTitle
     */
    public Object getPopularTitle() {
        return popularTitle;
    }

    /**
     * 
     * @param popularTitle
     *     The popular_title
     */
    public void setPopularTitle(Object popularTitle) {
        this.popularTitle = popularTitle;
    }

    /**
     * 
     * @return
     *     The relatedBillIds
     */
    public List<Object> getRelatedBillIds() {
        return relatedBillIds;
    }

    /**
     * 
     * @param relatedBillIds
     *     The related_bill_ids
     */
    public void setRelatedBillIds(List<Object> relatedBillIds) {
        this.relatedBillIds = relatedBillIds;
    }

    /**
     * 
     * @return
     *     The shortTitle
     */
    public Object getShortTitle() {
        return shortTitle;
    }

    /**
     * 
     * @param shortTitle
     *     The short_title
     */
    public void setShortTitle(Object shortTitle) {
        this.shortTitle = shortTitle;
    }

    /**
     * 
     * @return
     *     The sponsor
     */
    public Sponsor getSponsor() {
        return sponsor;
    }

    /**
     * 
     * @param sponsor
     *     The sponsor
     */
    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * 
     * @return
     *     The sponsorId
     */
    public String getSponsorId() {
        return sponsorId;
    }

    /**
     * 
     * @param sponsorId
     *     The sponsor_id
     */
    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    /**
     * 
     * @return
     *     The urls
     */
    public Urls getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    /**
     * 
     * @return
     *     The withdrawnCosponsorsCount
     */
    public Integer getWithdrawnCosponsorsCount() {
        return withdrawnCosponsorsCount;
    }

    /**
     * 
     * @param withdrawnCosponsorsCount
     *     The withdrawn_cosponsors_count
     */
    public void setWithdrawnCosponsorsCount(Integer withdrawnCosponsorsCount) {
        this.withdrawnCosponsorsCount = withdrawnCosponsorsCount;
    }

    /**
     * 
     * @return
     *     The lastVersion
     */
    public LastVersion getLastVersion() {
        return lastVersion;
    }

    /**
     * 
     * @param lastVersion
     *     The last_version
     */
    public void setLastVersion(LastVersion lastVersion) {
        this.lastVersion = lastVersion;
    }

}
