package com.lumivote.lumivote.api.sunlight_responses.bills;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class History {

    @Expose
    private Boolean active;
    @SerializedName("awaiting_signature")
    @Expose
    private Boolean awaitingSignature;
    @Expose
    private Boolean enacted;
    @Expose
    private Boolean vetoed;

    /**
     * @return The active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active The active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return The awaitingSignature
     */
    public Boolean getAwaitingSignature() {
        return awaitingSignature;
    }

    /**
     * @param awaitingSignature The awaiting_signature
     */
    public void setAwaitingSignature(Boolean awaitingSignature) {
        this.awaitingSignature = awaitingSignature;
    }

    /**
     * @return The enacted
     */
    public Boolean getEnacted() {
        return enacted;
    }

    /**
     * @param enacted The enacted
     */
    public void setEnacted(Boolean enacted) {
        this.enacted = enacted;
    }

    /**
     * @return The vetoed
     */
    public Boolean getVetoed() {
        return vetoed;
    }

    /**
     * @param vetoed The vetoed
     */
    public void setVetoed(Boolean vetoed) {
        this.vetoed = vetoed;
    }

}
