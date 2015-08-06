package com.lumivote.lumivote.bus;

import com.lumivote.lumivote.api.sunlight_responses.legislators.Result;

import java.util.List;

/**
 * Created by alex on 8/6/15.
 */
public class SunlightLegislatorsEvent extends AbstractSunlightEvent{

    public enum Type
    {
        COMPLETED,
        STARTED
    }

    private List<Result> legislators;

    public SunlightLegislatorsEvent(Type type, List<Result> legislators)
    {
        super(type);
        this.legislators = legislators;
    }

    public List<Result> getLegislators(){
        return legislators;
    }
}
