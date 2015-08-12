package com.lumivote.lumivote.bus;

import com.lumivote.lumivote.api.huffpost_responses.republican_primary_polls.Estimate;

import java.util.List;

/**
 * Created by alex on 8/11/15.
 */
public class HuffPostRepublicanPrimaryPollsEvent extends AbstractHuffPostEvent{

    public enum Type
    {
        COMPLETED,
        STARTED
    }

    private List<Estimate> republicanPolls;

    public HuffPostRepublicanPrimaryPollsEvent(Type type, List<Estimate> republicanPolls)
    {
        super(type);
        this.republicanPolls = republicanPolls;
    }

    public List<Estimate> getRepublicanPolls(){
        return republicanPolls;
    }
}
