package com.lumivote.lumivote.ui.candidate_tab;

/**
 * Created by alex on 12/7/15.
 */
public class CandidateDataAdapter{

    private String name;

    private String imageURL;

    private String description;

    public CandidateDataAdapter(String name, String imageURL, String description) {
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
