package com.lumivote.lumivote.api.lumivote_responses.candidates;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Candidate {

    @Expose
    private String ID;
    @Expose
    private String fName;
    @Expose
    private String mName;
    @Expose
    private String lName;
    @Expose
    private String party;
    @Expose
    private String occupation;
    @Expose
    private String birthdate;
    @Expose
    private String spouseFName;
    @Expose
    private String spouseMName;
    @Expose
    private String spouseLName;
    @Expose
    private String bio;
    @Expose
    private String twitter;
    @Expose
    private String url;

    /**
     * @return The ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID The ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return The fName
     */
    public String getFName() {
        return fName;
    }

    /**
     * @param fName The fName
     */
    public void setFName(String fName) {
        this.fName = fName;
    }

    /**
     * @return The mName
     */
    public String getMName() {
        return mName;
    }

    /**
     * @param mName The mName
     */
    public void setMName(String mName) {
        this.mName = mName;
    }

    /**
     * @return The lName
     */
    public String getLName() {
        return lName;
    }

    /**
     * @param lName The lName
     */
    public void setLName(String lName) {
        this.lName = lName;
    }

    /**
     * @return The party
     */
    public String getParty() {
        return party;
    }

    /**
     * @param party The party
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     * @return The occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation The occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * @return The birthdate
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate The birthdate
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * @return The spouseFName
     */
    public String getSpouseFName() {
        return spouseFName;
    }

    /**
     * @param spouseFName The spouseFName
     */
    public void setSpouseFName(String spouseFName) {
        this.spouseFName = spouseFName;
    }

    /**
     * @return The spouseMName
     */
    public String getSpouseMName() {
        return spouseMName;
    }

    /**
     * @param spouseMName The spouseMName
     */
    public void setSpouseMName(String spouseMName) {
        this.spouseMName = spouseMName;
    }

    /**
     * @return The spouseLName
     */
    public String getSpouseLName() {
        return spouseLName;
    }

    /**
     * @param spouseLName The spouseLName
     */
    public void setSpouseLName(String spouseLName) {
        this.spouseLName = spouseLName;
    }

    /**
     * @return The bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio The bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * @return The twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * @param twitter The twitter
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
