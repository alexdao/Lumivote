package com.lumivote.lumivote.api;

/**
 * Created by alex on 8/6/15.
 */
public class UnitedStatesImagesURLBuilder {

    private static final String api_base = "https://theunitedstates.io/images/congress/original/";

    private StringBuilder url;

    public UnitedStatesImagesURLBuilder() {
        url = new StringBuilder();
        url.append(api_base);
    }

    public void setBioID(String bioID) {
        url.append(bioID);
    }

    public String getPhotoURL() {
        url.append(".jpg");
        return url.toString();
    }
}
