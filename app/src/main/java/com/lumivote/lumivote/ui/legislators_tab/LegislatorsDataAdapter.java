package com.lumivote.lumivote.ui.legislators_tab;

/**
 * Created by alex on 8/12/15.
 */
public class LegislatorsDataAdapter {

    private String name;
    private String chamber;
    private String state;
    private String photoURL;

    private String mainTitle;
    private String mainDescription;

    private StringBuilder description = new StringBuilder();

    public LegislatorsDataAdapter(String name, String chamber, String state, String photoURL){
        this.name = name;
        this.chamber = chamber;
        this.state = state;
        this.photoURL = photoURL;

        formatData();
        setFormattedData();
    }

    private void formatData() {
        chamber = capitalizeFirstLetter(chamber);
        description.append(chamber)
                .append(" - ")
                .append(state);
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    private void setFormattedData() {
        mainTitle = name;
        mainDescription = description.toString();
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public String getMainDescription() {
        return mainDescription;
    }

    public String getPhotoURL() {
        return photoURL;
    }

}
