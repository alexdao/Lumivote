package com.lumivote.lumivote.bus;

import com.lumivote.lumivote.api.sunlight_responses.votes.Result;

import java.util.List;

/**
 * Created by alex on 8/6/15.
 */
public class SunlightVotesEvent extends AbstractSunlightEvent {

    public enum Type {
        COMPLETED,
        STARTED
    }

    private List<Result> votesList;

    public SunlightVotesEvent(Type type, List<Result> votesList) {
        super(type);
        this.votesList = votesList;
    }

    public List<Result> getVotesList() {
        return votesList;
    }

}
