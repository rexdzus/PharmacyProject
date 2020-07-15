package Controller;

import Connect.ConnectionClass;
import Main.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    //public TextField text_entered;
    public TextField username;
    public TextField password;

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }


    public void register(ActionEvent actionEvent) throws IOException {
        Parent registerPage = FXMLLoader.load(getClass().getResource("../FXML/registration.fxml"));
        Scene registerScene = new Scene(registerPage);
        Stage setStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        setStage.setScene(registerScene);
        setStage.show();
    }


    public void login(ActionEvent actionEvent) throws IOException {

       // User user = new User("user","pass", "rex","yei",0);

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            Statement statement = connection.createStatement();
            String sqlLogin = "Select * from patients where userName = '"+getUsername()+"' AND password = '"+getPassword()+"';";
            ResultSet resultPatients = statement.executeQuery(sqlLogin);

            if (resultPatients.next()) {
                String thisUser = getUsername();
                PatientUIController.beta = thisUser;
                Parent patientPage = FXMLLoader.load(getClass().getResource("../FXML/patientUI.fxml"));
                Scene patientScene = new Scene(patientPage);
                Stage setStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                setStage.setScene(patientScene);
                setStage.show();
            } else {

            }

        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }

    public void loginDr(ActionEvent actionEvent) throws SQLException, IOException {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            Statement statement1 = connection.createStatement();
            String sqlLoginDr = "Select * from doctors where userName = '" + getUsername() + "' AND password = '" + getPassword() + "';";
            ResultSet resultDrs = statement1.executeQuery(sqlLoginDr);
            if (resultDrs.next()) {
                String thisUser = getUsername();
                DoctorUIController.alpha = thisUser;
                Parent registerPage = FXMLLoader.load(getClass().getResource("../FXML/doctorUI.fxml"));
                Scene registerScene = new Scene(registerPage);
                Stage setStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                setStage.setScene(registerScene);
                setStage.show();
            } else {
            }
           } catch (SQLException | IOException e){
        e.printStackTrace();
    }
     }
}
