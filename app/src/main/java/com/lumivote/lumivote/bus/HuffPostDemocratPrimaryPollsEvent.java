package com.lumivote.lumivote.bus;

import com.lumivote.lumivote.api.huffpost_responses.democrat_primary_polls.Estimate;

import java.util.List;

/**
 * Created by alex on 8/12/15.
 */
public class HuffPostDemocratPrimaryPollsEvent extends AbstractHuffPostEvent{

    public enum Type
    {
        COMPLETED,
        STARTED
    }

    private List<Estimate> democrats;

    public HuffPostDemocratPrimaryPollsEvent(Type type, List<Estimate> democrats){
        super(type);
        this.democrats = democrats;
    }

    public List<Estimate> getDemocratPolls(){
        return democrats;
    }

}
