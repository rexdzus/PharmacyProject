package Main;

public class Doctor extends User{
    public  String firstname;
    public  String lastname;

    public Doctor(String username, String password, String firstname, String lastname) {
        super(username, password);
        this.firstname = firstname;
        this.lastname = lastname;
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
}
