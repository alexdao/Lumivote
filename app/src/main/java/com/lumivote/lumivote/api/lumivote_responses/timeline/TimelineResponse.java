
package com.lumivote.lumivote.api.lumivote_responses.timeline;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class TimelineResponse {

    @Expose
    private List<Timeline> timeline = new ArrayList<Timeline>();

    /**
     * 
     * @return
     *     The timeline
     */
    public List<Timeline> getTimeline() {
        return timeline;
    }

    /**
     * 
     * @param timeline
     *     The timeline
     */
    public void setTimeline(List<Timeline> timeline) {
        this.timeline = timeline;
    }

}
