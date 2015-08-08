package com.lumivote.lumivote.ui.bills_tab;

/**
 * Created by alex on 8/7/15.
 */
public class BillsDataAdapter {

    private String mainTitle;
    private String mainDescription;
    private String leftTitle;
    private String leftDescription;

    private String billType;
    private String billNumber;
    private String chamber;
    private String title;
    private String action;

    BillsDataAdapter(String billType, String billNumber, String chamber, String title, String action) {
        this.billType = billType;
        this.billNumber = billNumber;
        this.chamber = chamber;
        this.title = title;
        this.action = action;
        formatData();
        setFormattedData();
    }

    private void formatData() {
        chamber = capitalizeFirstLetter(chamber);
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    private void setFormattedData(){
        mainTitle = billType + " " + billNumber;
        mainDescription = action;
        leftTitle = title;
        leftDescription = chamber;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public String getMainDescription() {
        return mainDescription;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public String getLeftDescription() {
        return leftDescription;
    }
}