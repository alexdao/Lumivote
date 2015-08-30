
package com.lumivote.lumivote.api.lumivote_responses.timeline;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Timeline {

    @Expose
    private String eventID;
    @Expose
    private String name;
    @Expose
    private String date;
    @Expose
    private String time;
    @Expose
    private String party;
    @Expose
    private String city;
    @Expose
    private String state;
    @Expose
    private String type;
    @Expose
    private String description;

    /**
     * 
     * @return
     *     The eventID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * 
     * @param eventID
     *     The eventID
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The time
     */
    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The party
     */
    public String getParty() {
        return party;
    }

    /**
     * 
     * @param party
     *     The party
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
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
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
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

}
