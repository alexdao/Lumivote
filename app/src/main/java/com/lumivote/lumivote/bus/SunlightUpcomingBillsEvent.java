package com.lumivote.lumivote.bus;

import com.lumivote.lumivote.api.sunlight_responses.upcoming_bills.Result;

import java.util.List;

/**
 * Created by alex on 8/6/15.
 */
public class SunlightUpcomingBillsEvent extends AbstractSunlightEvent{

    public enum Type
    {
        COMPLETED,
        STARTED
    }

    private List<Result> upcomingBillsList;

    public SunlightUpcomingBillsEvent(Type type, List<Result> upcomingBillsList)
    {
        super(type);
        this.upcomingBillsList = upcomingBillsList;
    }

    public List<Result> getUpcomingBillsList(){
        return upcomingBillsList;
    }
}
