package Main;

import java.sql.Date;

public class Appointment {

    String username;
    String comments;
    Date date;
    String myDoctor;

    public Appointment(String username, String comments, Date date, String myDoctor) {
        this.username = username;
        this.comments = comments;
        this.date = date;
        this.myDoctor = myDoctor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMyDoctor() {
        return myDoctor;
    }

    public void setMyDoctor(String myDoctor) {
        this.myDoctor = myDoctor;
    }
}
