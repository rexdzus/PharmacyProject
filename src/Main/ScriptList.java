package Main;

import java.util.Date;

public class ScriptList {
    String username;
    String medicine;
    Date date;
    String amount;
    String weeks;
    String numberPill;


    public ScriptList(String username, String medicine, Date date, String amount, String weeks, String numberPill) {
        this.username = username;
        this.medicine = medicine;
        this.date = date;
        this.amount = amount;
        this.weeks = weeks;
        this.numberPill = numberPill;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getNumberPill() {
        return numberPill;
    }

    public void setNumberPill(String numberPill) {
        this.numberPill = numberPill;
    }
}
