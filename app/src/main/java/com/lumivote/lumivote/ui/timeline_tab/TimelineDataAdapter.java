package com.lumivote.lumivote.ui.timeline_tab;

/**
 * Created by alex on 8/30/15.
 */
public class TimelineDataAdapter {

    private String title;
    private String description;

    TimelineDataAdapter(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
