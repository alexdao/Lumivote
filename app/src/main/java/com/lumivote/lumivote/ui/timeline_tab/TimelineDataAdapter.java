package com.lumivote.lumivote.ui.timeline_tab;

/**
 * Created by alex on 8/30/15.
 */
public class TimelineDataAdapter {

    private String mainTitle;
    private String mainDescription;
    private String leftTitle;
    private String leftDescription;

    TimelineDataAdapter(String mainTitle, String mainDescription, String leftTitle, String leftDescription) {
        this.mainTitle = mainTitle;
        this.mainDescription = mainDescription;
        this.leftTitle = leftTitle;
        this.leftDescription = leftDescription;
    }

    public String getMainDescription() {
        return mainDescription;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public String getLeftDescription() {
        return leftDescription;
    }

    public String getMainTitle() {
        return mainTitle;
    }
}
