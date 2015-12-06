package com.lumivote.lumivote.ui.timeline_tab;

/**
 * Created by alex on 8/30/15.
 */
public class TimelineDataAdapter {

    private String title;
    private String description;
    private String partyType;

    TimelineDataAdapter(String title, String description, String partyType) {
        this.title = title;
        this.description = description;
        this.partyType = partyType;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getPartyType(){
        return partyType;
    }
}
