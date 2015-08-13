package com.lumivote.lumivote.bus;

import com.lumivote.lumivote.api.huffpost_responses.democrat_primary_polls.DemocratPollResponse;

/**
 * Created by alex on 8/12/15.
 */
public class HuffPostDemocratPrimaryPollsEvent extends AbstractHuffPostEvent{

    public enum Type
    {
        COMPLETED,
        STARTED
    }

    private DemocratPollResponse democrats;

    public HuffPostDemocratPrimaryPollsEvent(Type type, DemocratPollResponse democrats){
        super(type);
        this.democrats = democrats;
    }

    public DemocratPollResponse getDemocratPolls(){
        return democrats;
    }

}
