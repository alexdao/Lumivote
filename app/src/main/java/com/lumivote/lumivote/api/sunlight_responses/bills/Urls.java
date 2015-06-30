
package com.lumivote.lumivote.api.sunlight_responses.bills;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Urls {

    @Expose
    private String congress;
    @Expose
    private String govtrack;
    @Expose
    private String opencongress;

    /**
     * 
     * @return
     *     The congress
     */
    public String getCongress() {
        return congress;
    }

    /**
     * 
     * @param congress
     *     The congress
     */
    public void setCongress(String congress) {
        this.congress = congress;
    }

    /**
     * 
     * @return
     *     The govtrack
     */
    public String getGovtrack() {
        return govtrack;
    }

    /**
     * 
     * @param govtrack
     *     The govtrack
     */
    public void setGovtrack(String govtrack) {
        this.govtrack = govtrack;
    }

    /**
     * 
     * @return
     *     The opencongress
     */
    public String getOpencongress() {
        return opencongress;
    }

    /**
     * 
     * @param opencongress
     *     The opencongress
     */
    public void setOpencongress(String opencongress) {
        this.opencongress = opencongress;
    }

}
