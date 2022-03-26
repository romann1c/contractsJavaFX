package com.romann1c.contracts.database.purejdbc;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Contract {
    private Integer number;
    private String name;
    private Date dateOfSign;
    private Date dateOfUpdate;
    private Boolean valid;

    public Contract(Integer number, String name, String dateOfSign, String dateOfUpdate) {
        this.number = number;
        this.name = name;
        Date tempDateOfSign = getDate(dateOfSign);
        Date tempDateOfUpdate = getDate(dateOfUpdate);
        if (tempDateOfSign.getTime() < tempDateOfUpdate.getTime()){
            this.dateOfSign = tempDateOfSign;
            this.dateOfUpdate = tempDateOfUpdate;
        } else {
            this.dateOfSign = tempDateOfSign;
            this.dateOfUpdate = tempDateOfSign;
        }
        this.valid = isValid(this.dateOfUpdate);

    }

    public Contract(Integer number, String name, Date dateOfSign, Date dateOfUpdate) {
        this.number = number;
        this.name = name;
        if(dateOfSign.getTime() < dateOfUpdate.getTime()){
            this.dateOfSign = dateOfSign;
            this.dateOfUpdate = dateOfUpdate;
        } else {
            this.dateOfUpdate = dateOfSign;
            this.dateOfSign = dateOfSign;
        }
        this.valid = isValid(this.dateOfUpdate);


    }

    public Contract(Integer number, String name, Date dateOfSign) {
        this.number = number;
        this.name = name;
        this.dateOfUpdate = dateOfSign;
        this.dateOfSign = dateOfSign;
        this.valid = isValid(this.dateOfUpdate);
    }

    public Contract(Integer number, String name, String dateOfSign) {
        this.number = number;
        this.name = name;
        this.dateOfSign = getDate(dateOfSign);
        this.dateOfUpdate = this.dateOfSign;
        this.valid = isValid(this.dateOfUpdate);
    }

    private Date getDate(String string_date) {
        Date date = Date.valueOf(string_date); // yyyy-mm-dd
        return date;
    }

    private Boolean isValid(Date date) {
        Calendar calendarOfContractDate = new GregorianCalendar();
        calendarOfContractDate.setTime(date);
        Calendar today = new GregorianCalendar();
        java.util.Date todayDate = new java.util.Date();
        today.setTime(todayDate);
        int yearsDiff = today.get(Calendar.YEAR)
                - calendarOfContractDate.get(Calendar.YEAR);
        int monthsDiff = today.get(Calendar.MONTH)
                - calendarOfContractDate.get(Calendar.MONTH);
        int ageInMonths = yearsDiff*12 + monthsDiff;
        if (ageInMonths <= 2) return true;
        else return false;
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfSign() {
        return dateOfSign;
    }

    public Date getDateOfUpdate() {
        return dateOfUpdate;
    }

    public Boolean getValid() {
        return valid;
    }

    @Override
    public String toString() {
        return "Договор № " + number + ", \"" + name + "\", от " + dateOfSign + ", последние изменения от " + dateOfUpdate + ".";
    }
}
