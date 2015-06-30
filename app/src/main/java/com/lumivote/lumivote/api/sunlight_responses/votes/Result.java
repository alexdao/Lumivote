package com.lumivote.lumivote.api.sunlight_responses.votes;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Result {

    @SerializedName("bill_id")
    @Expose
    private String billId;
    @Expose
    private String chamber;
    @Expose
    private Integer congress;
    @Expose
    private Integer number;
    @Expose
    private String question;
    @Expose
    private String required;
    @Expose
    private String result;
    @SerializedName("roll_id")
    @Expose
    private String rollId;
    @SerializedName("roll_type")
    @Expose
    private String rollType;
    @Expose
    private String source;
    @Expose
    private String url;
    @SerializedName("vote_type")
    @Expose
    private String voteType;
    @SerializedName("voted_at")
    @Expose
    private String votedAt;
    @Expose
    private Integer year;
    @SerializedName("amendment_id")
    @Expose
    private String amendmentId;

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
     *     The question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 
     * @param question
     *     The question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 
     * @return
     *     The required
     */
    public String getRequired() {
        return required;
    }

    /**
     * 
     * @param required
     *     The required
     */
    public void setRequired(String required) {
        this.required = required;
    }

    /**
     * 
     * @return
     *     The result
     */
    public String getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 
     * @return
     *     The rollId
     */
    public String getRollId() {
        return rollId;
    }

    /**
     * 
     * @param rollId
     *     The roll_id
     */
    public void setRollId(String rollId) {
        this.rollId = rollId;
    }

    /**
     * 
     * @return
     *     The rollType
     */
    public String getRollType() {
        return rollType;
    }

    /**
     * 
     * @param rollType
     *     The roll_type
     */
    public void setRollType(String rollType) {
        this.rollType = rollType;
    }

    /**
     * 
     * @return
     *     The source
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(String source) {
        this.source = source;
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
     *     The voteType
     */
    public String getVoteType() {
        return voteType;
    }

    /**
     * 
     * @param voteType
     *     The vote_type
     */
    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    /**
     * 
     * @return
     *     The votedAt
     */
    public String getVotedAt() {
        return votedAt;
    }

    /**
     * 
     * @param votedAt
     *     The voted_at
     */
    public void setVotedAt(String votedAt) {
        this.votedAt = votedAt;
    }

    /**
     * 
     * @return
     *     The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 
     * @return
     *     The amendmentId
     */
    public String getAmendmentId() {
        return amendmentId;
    }

    /**
     * 
     * @param amendmentId
     *     The amendment_id
     */
    public void setAmendmentId(String amendmentId) {
        this.amendmentId = amendmentId;
    }

}
