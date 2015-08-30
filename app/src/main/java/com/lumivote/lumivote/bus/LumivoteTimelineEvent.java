package com.lumivote.lumivote.bus;

import com.lumivote.lumivote.api.lumivote_responses.timeline.Timeline;

import java.util.List;

/**
 * Created by alex on 8/30/15.
 */
public class LumivoteTimelineEvent extends AbstractLumivoteEvent{

    public enum Type
    {
        COMPLETED,
        STARTED
    }

    private List<Timeline> timelineList;

    public LumivoteTimelineEvent(Type type, List<Timeline> timelineList){
        super(type);
        this.timelineList = timelineList;
    }

    public List<Timeline> getTimelineList(){
        return timelineList;
    }
}
