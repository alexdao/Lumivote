package com.lumivote.lumivote.ui;

/**
 * Created by alex on 8/10/15.
 */
public class DateFormatter {

    private String date;

    public DateFormatter(String date){
        this.date = date;
    }

    public String getFormattedDate(){
        int indexOfFirstHyphen = date.indexOf("-");
        int indexOfSecondHyphen = date.indexOf("-", indexOfFirstHyphen+1);

        String monthString = date.substring(indexOfFirstHyphen+1, indexOfSecondHyphen);
        int month = Integer.parseInt(monthString);
        monthString = convertMonthFromNumericToEnglish(month);

        String dayString = date.substring(indexOfSecondHyphen+1);
        int day = Integer.parseInt(dayString);

        StringBuilder formattedDateBuilder = new StringBuilder();
        formattedDateBuilder.append(monthString)
                .append(" ")
                .append(day)
                .append(", ")
                .append(date.substring(0, indexOfFirstHyphen));
        return formattedDateBuilder.toString();
    }

    private String convertMonthFromNumericToEnglish(int month){
        String monthString;
        switch (month) {
            case 1:  monthString = "Jan";
                break;
            case 2:  monthString = "Feb";
                break;
            case 3:  monthString = "Mar";
                break;
            case 4:  monthString = "Apr";
                break;
            case 5:  monthString = "May";
                break;
            case 6:  monthString = "Jun";
                break;
            case 7:  monthString = "Jul";
                break;
            case 8:  monthString = "Aug";
                break;
            case 9:  monthString = "Sep";
                break;
            case 10: monthString = "Oct";
                break;
            case 11: monthString = "Nov";
                break;
            case 12: monthString = "Dec";
                break;
            default: monthString = "Invalid month";
                break;
        }
        return monthString;
    }

    public void truncateTime() {
        int indexOfT = date.indexOf("T");
        date = date.substring(0, indexOfT);
    }

}