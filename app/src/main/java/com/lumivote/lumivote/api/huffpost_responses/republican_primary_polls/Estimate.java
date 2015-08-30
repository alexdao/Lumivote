package com.lumivote.lumivote.api.huffpost_responses.republican_primary_polls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Estimate {

    @Expose
    private String choice;
    @Expose
    private Double value;
    @SerializedName("lead_confidence")
    @Expose
    private Object leadConfidence;
    @SerializedName("first_name")
    @Expose
    private Object firstName;
    @SerializedName("last_name")
    @Expose
    private Object lastName;
    @Expose
    private Object party;
    @Expose
    private Object incumbent;

    /**
     * @return The choice
     */
    public String getChoice() {
        return choice;
    }

    /**
     * @param choice The choice
     */
    public void setChoice(String choice) {
        this.choice = choice;
    }

    /**
     * @return The value
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value The value
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * @return The leadConfidence
     */
    public Object getLeadConfidence() {
        return leadConfidence;
    }

    /**
     * @param leadConfidence The lead_confidence
     */
    public void setLeadConfidence(Object leadConfidence) {
        this.leadConfidence = leadConfidence;
    }

    /**
     * @return The firstName
     */
    public Object getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The first_name
     */
    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    /**
     * @return The lastName
     */
    public Object getLastName() {
        return lastName;
    }

    /**
     * @param lastName The last_name
     */
    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The party
     */
    public Object getParty() {
        return party;
    }

    /**
     * @param party The party
     */
    public void setParty(Object party) {
        this.party = party;
    }

    /**
     * @return The incumbent
     */
    public Object getIncumbent() {
        return incumbent;
    }

    /**
     * @param incumbent The incumbent
     */
    public void setIncumbent(Object incumbent) {
        this.incumbent = incumbent;
    }

}
