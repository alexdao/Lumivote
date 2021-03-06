package com.lumivote.lumivote.ui.votes_tab;

import com.lumivote.lumivote.ui.DateFormatter;

/**
 * Created by alex on 8/7/15.
 */
public class VotesDataAdapter {

    private String billID;
    private String result;
    private String question;
    private String date;
    private String chamber;

    private String mainTitle;
    private String mainDescription;
    private String leftTitle;
    private String leftDescription;

    public VotesDataAdapter(String billID, String result, String question, String date, String chamber) {
        this.billID = billID;
        this.result = result;
        this.question = question;
        this.date = date;
        this.chamber = chamber;
        formatData();
        setFormattedData();
    }

    private void formatData() {
        billID = formatBillID(this.billID);
        chamber = capitalizeFirstLetter(chamber);
        DateFormatter dateFormatter = new DateFormatter(date);
        dateFormatter.truncateTime();
        date = dateFormatter.getFormattedDate();
    }

    private String formatBillID(String s) {
        s = truncateBillID(s);
        s = formatTypeOfBill(s);
        return s;
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    private String formatTypeOfBill(String s) {
        if (s.startsWith("hres")) {
            return s.replace("hres", "H. Res. ");
        } else if (s.startsWith("hr")) {
            return s.replace("hr", "H. R. ");
        } else if (s.startsWith("hjres")) {
            return s.replace("hjres", "H. J. Res. ");
        } else if (s.startsWith("hconres")) {
            return s.replace("hconres", "H. Con. Res ");
        } else if (s.startsWith("sconres")) {
            return s.replace("sconres", "S. Con. Res. ");
        } else if (s.startsWith("sjres")) {
            return s.replace("sjres", "S. J. Res. ");
        } else if (s.startsWith("sres")) {
            return s.replace("sres", "S. Res. ");
        } else if (s.startsWith("s")) {
            return s.replace("s", "S. ");
        } else {
            return s;
        }
    }

    private String truncateBillID(String s) {
        int indexOfHyphen = s.indexOf("-");
        if (indexOfHyphen > -1) {
            return s.substring(0, indexOfHyphen);
        } else {
            return s;
        }
    }

    private void setFormattedData() {
        leftTitle = billID + " " + result;
        leftDescription = question;
        mainTitle = date;
        mainDescription = chamber;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public String getMainDescription() {
        return mainDescription;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public String getLeftDescription() {
        return leftDescription;
    }

}
