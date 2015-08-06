package com.lumivote.lumivote.bus;

import com.lumivote.lumivote.api.sunlight_responses.bills.Result;

import java.util.List;

/**
 * Created by alex on 8/6/15.
 */
public class SunlightBillsEvent extends AbstractSunlightEvent{

    public enum Type
    {
        COMPLETED,
        STARTED
    }

    private List<Result> votesList;

    public SunlightBillsEvent(Type type, List<Result> votesList)
    {
        super(type);
        this.votesList = votesList;
    }

    public List<Result> getVotesList(){
        return votesList;
    }

}
