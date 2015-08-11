package com.lumivote.lumivote.ui.bills_tab;

import com.lumivote.lumivote.ui.DateFormatter;

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
    private String date;

    BillsDataAdapter(String billType, String billNumber, String chamber, String title, String date) {
        this.billType = billType;
        this.billNumber = billNumber;
        this.chamber = chamber;
        this.title = title;
        this.date = date;
        formatData();
        setFormattedData();
    }

    private void formatData() {
        chamber = capitalizeFirstLetter(chamber);
        billType = formatTypeOfBill(billType);
        DateFormatter dateFormatter = new DateFormatter(date);
        date = dateFormatter.getFormattedDate();
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    private String formatTypeOfBill(String s){
        if(s.startsWith("hres")){
            return s.replace("hres", "H. Res. ");
        }
        else if(s.startsWith("hr")){
            return s.replace("hr", "H. R. ");
        }
        else if(s.startsWith("hjres")){
            return s.replace("hjres", "H. J. Res. ");
        }
        else if(s.startsWith("hconres")){
            return s.replace("hconres", "H. Con. Res ");
        }
        else if(s.startsWith("sconres")){
            return s.replace("sconres", "S. Con. Res. ");
        }
        else if(s.startsWith("sjres")){
            return s.replace("sjres", "S. J. Res. ");
        }
        else if(s.startsWith("sres")){
            return s.replace("sres", "S. Res. ");
        }
        else if(s.startsWith("s")){
            return s.replace("s", "S. ");
        }
        else{
            return s;
        }
    }

    private void setFormattedData(){
        mainTitle = billType + billNumber;
        mainDescription = date;
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