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
    private Object SQLException;
    private Object IOException;

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

        try{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            Statement statement = connection.createStatement();
            String sqlLogin = "Select * from patients where patients.username = '"+getUsername()+"' AND password = '"+getPassword()+"';";
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
                ConnectionClass ConnectionClass = new ConnectionClass();
                Connection pharmConnection = ConnectionClass.getConnection();

                Statement conn = pharmConnection.createStatement();
                String pharmSqlLogin = "Select * from pharmacy where pharmacy.username = '" + getUsername() + "' AND password = '" + getPassword() + "';";
                ResultSet pharmResultPatients = conn.executeQuery(pharmSqlLogin);
                if (pharmResultPatients.next()) {
                    String thisUser = getUsername();
                    PatientUIController.beta = thisUser;
                    Parent patientPage = FXMLLoader.load(getClass().getResource("../FXML/pharmacyUI.fxml"));
                    Scene patientScene = new Scene(patientPage);
                    Stage setStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    setStage.setScene(patientScene);
                    setStage.show();
                } else {
                    System.out.println("Login Failed");
                }}

        } catch (SQLException | IOException e){
            e.printStackTrace();
        }

}
    public void loginDr(ActionEvent actionEvent) throws SQLException, IOException {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            Statement statement1 = connection.createStatement();
            String sqlLoginDr = "Select * from doctors where doctors.userName = '" + getUsername() + "' AND password = '" + getPassword() + "';";
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
}}
