package com.lumivote.lumivote.bus;

import com.lumivote.lumivote.api.lumivote_responses.candidates.Candidate;

import java.util.List;

/**
 * Created by alex on 8/30/15.
 */
public class LumivoteCandidateEvent extends AbstractLumivoteEvent {

    public enum Type {
        COMPLETED,
        STARTED
    }

    private List<Candidate> candidateList;

    public LumivoteCandidateEvent(Type type, List<Candidate> candidateList) {
        super(type);
        this.candidateList = candidateList;
    }

    public List<Candidate> getCandidateList() {
        return candidateList;
    }
}
