package Main;


import java.sql.Array;
import Main.Medicine;
import javafx.beans.property.SimpleStringProperty;

public class Patient extends User {
    public  String username;
    public  String password;
    public  String firstName;
    public  String lastName;
    public  String myDoctor;

    public Patient(String username, String password, String firstName, String lastName, String myDoctor) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.myDoctor = myDoctor;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMyDoctor() {
        return myDoctor;
    }

    public void setMyDoctor(String myDoctor) {
        this.myDoctor = myDoctor;
    }

}
