
package com.lumivote.lumivote.api.lumivote_responses.candidates;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class CandidateResponse {

    @Expose
    private List<Candidate> candidates = new ArrayList<Candidate>();

    /**
     * 
     * @return
     *     The candidates
     */
    public List<Candidate> getCandidates() {
        return candidates;
    }

    /**
     * 
     * @param candidates
     *     The candidates
     */
    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

}
