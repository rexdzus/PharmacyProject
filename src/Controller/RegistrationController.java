package Controller;

import Connect.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    public TextField firstName;
    public TextField lastName;
    public TextField username;
    public TextField password;
    public CheckBox isDoctor;
    public CheckBox isPharmacy;

    public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public int buttonReader(){
        boolean isDoctorSelected = isDoctor.isSelected();
        boolean isPharmacySelected = isPharmacy.isSelected();

        int isDoc = 0;  //Patient
        if (isDoctorSelected == true) {
            isDoc = 1;  //Doctors
        } else if (isPharmacySelected == true){
            isDoc = 2;     //Pharmacy
        }
        return isDoc;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void register(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println(getUsername());
        System.out.println(getPassword());
        System.out.println(getFirstName());
        System.out.println(getLastName());

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        if (buttonReader() == 0) {

            Statement testuser = connection.createStatement();
            String test1 = "insert into patients (userName, password, firstname, lastname, myDoctor) Values ('"
                    + getUsername() + "', '" + getPassword() + "', '" + getFirstName() + "', '" + getLastName() + "', 'noDoc');";
            testuser.executeUpdate(test1);
            connection.close();
            System.out.println(buttonReader());
        } else if (buttonReader() == 1){
            Statement testuser = connection.createStatement();
            String test1 = "insert into doctors (userName, password, firstname, lastname) Values ('"
                    + getUsername() + "', '" + getPassword() + "', '" + getFirstName() + "', '" + getLastName() + "');";
            testuser.executeUpdate(test1);
            connection.close();
            System.out.println(buttonReader());
        } else {
            Statement testuser = connection.createStatement();
            String test1 = "insert into pharmacy (userName, password, firstname, lastname) Values ('"
                    + getUsername() + "', '" + getPassword() + "', '" + getFirstName() + "', '" + getLastName() + "', '" + buttonReader() + "');";
            testuser.executeUpdate(test1);
            connection.close();
            System.out.println(buttonReader());

        }
        Parent registerPage = FXMLLoader.load(getClass().getResource("../FXML/sample.fxml"));
        Scene registerScene = new Scene(registerPage);
        Stage setStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        setStage.setScene(registerScene);
        setStage.show();

    }
}
