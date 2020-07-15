package Main;


import java.sql.Array;
import Main.Medicine;
import javafx.beans.property.SimpleStringProperty;

public class Patient extends User {
        public  String firstname;
        public  String lastname;
        public  String myDoctor;

    public Patient(String username, String password, String firstname, String lastname, String myDoctor) {
        super(username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.myDoctor = myDoctor;
    }

    public Patient() {

    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMyDoctor() {
        return myDoctor;
    }

    public void setMyDoctor(String myDoctor) {
        this.myDoctor = myDoctor;
    }
}
