package Controller;

import Main.Appointment;
import Main.IssuesList;
import Main.Patient;
import Connect.ConnectionClass;

import Main.ScriptList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

/*
    FXML to database connection tutorial :https://youtu.be/tw_NXq08NUE
 */

public class DoctorUIController implements Initializable {

    public static String alpha;
    public Button add_patient;
    public Button delete_patient;
    public TextField first_name;
    public TextField last_name;

/*    public TableColumn firstnamecol;
    public TableColumn lastnamecol;*/
    @FXML
    public TableView<Patient> PatientsList;

    public TableView<IssuesList> Issues;

    public TableColumn firstnamecol;
    public TableColumn lastnamecol;

    public TableColumn usernamecol;
    public TableColumn severitycol;
    public TableColumn commentscol;
    public TextField condition_patient;
    public DatePicker date;

    public TableView<Appointment> Appointment;
    public TableColumn usernameAps;
    public TableColumn commentsAps;
    public TableColumn datecol;
    public Button setAppointment;


    public DoctorUIController() throws SQLException {}

    public String getFirstName() {
        return first_name.getText();
    }

    public String getLastName() {
        return last_name.getText();
    }

    public void initialize (URL url, ResourceBundle rb){
        updateTable();
        updateIssues();
        updateAppointment();
    }

    public void updateIssues (){
        try {
            usernamecol.setCellValueFactory(new PropertyValueFactory<IssuesList,String>("userName"));
            severitycol.setCellValueFactory(new PropertyValueFactory<IssuesList,String>("severity"));
            commentscol.setCellValueFactory(new PropertyValueFactory<IssuesList,String>("comments"));
            Issues.getItems().setAll(parseIssuesList());

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    List<IssuesList> listIssues = new ArrayList<>();
    public List<IssuesList> parseIssuesList() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement gettable = connection.createStatement();
        String patientSQL = "select * from issueslist where myDoctor = '" + alpha + "';";
        ResultSet st = gettable.executeQuery(patientSQL);
        connection.close();
        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list
        while(st.next()){
            String userName = st.getString("userName");
            String severity = st.getString("severity");
            String comments = st.getString("comments");
            String myDoctor = st.getString("myDoctor");
            IssuesList patL = new IssuesList(userName,severity,comments,myDoctor);
            listIssues.add(patL);

        }
        return listIssues;
    }
    public void updateTable(){
        try {
            firstnamecol.setCellValueFactory(new PropertyValueFactory<Patient,String>("firstName"));
            lastnamecol.setCellValueFactory(new PropertyValueFactory<Patient,String>("lastName"));
            PatientsList.getItems().setAll(parseUserList());

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    List<Patient> list = new ArrayList<>();
    public List<Patient> parseUserList() throws SQLException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement gettable = connection.createStatement();
        String patientSQL = "select * from patients where myDoctor = '" + alpha + "';";
        ResultSet st = gettable.executeQuery(patientSQL);

        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list
        while(st.next()){
            String username = st.getString("userName");
            String password = st.getString("password");
            String firstName = st.getString("firstname");
            String lastName = st.getString("lastname");
            String myDoctor = st.getString("myDoctor");
            Patient pat = new Patient(username,password,firstName,lastName,myDoctor);
            list.add(pat);
        }
        return list;
    }
    public void updateAppointment(){
        try {
            usernameAps.setCellValueFactory(new PropertyValueFactory<Appointment,String>("username"));
            commentsAps.setCellValueFactory(new PropertyValueFactory<Appointment,String>("comments"));
            datecol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("date"));
            Appointment.getItems().setAll(parseAppointmentList());

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    List<Appointment> appointments = new ArrayList<>();
    public List<Appointment> parseAppointmentList() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement gettable = connection.createStatement();
        String patientSQL = "select * from \"appointment\" where \"myDoctor\" = '" + alpha + "';";
        ResultSet st = gettable.executeQuery(patientSQL);
        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list
        while(st.next()){
            String username = st.getString("username");
            String comments = st.getString("comments");
            Date date = st.getDate("date");
            String myDoctor = st.getString("myDoctor");
            Appointment patL = new Appointment(username,comments,date,myDoctor);
            appointments.add(patL);

        }
        return appointments;
    }
    public void add_patient(ActionEvent actionEvent) throws SQLException, IOException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        Statement testuser = connection.createStatement();
        String test1 = "UPDATE patients SET mydoctor = '"+alpha+"' WHERE firstname = '"+getFirstName()+"' AND lastname = '"+getLastName()+"';";
        testuser.executeUpdate(test1);
        connection.close();
        updateTable();
        Parent registerPage = FXMLLoader.load(getClass().getResource("../FXML/doctorUI.fxml"));
        Scene registerScene = new Scene(registerPage);
        Stage setStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        setStage.setScene(registerScene);
        setStage.show();

    }
    public void delete_patient(ActionEvent actionEvent) throws SQLException, IOException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        Statement testuser = connection.createStatement();
        String test1 = "UPDATE patients SET myDoctor = 'noDoc' WHERE firstname = '"+getFirstName()+"' AND lastname = '"+getLastName()+"';";
        testuser.executeUpdate(test1);
        connection.close();
        updateTable();
        Parent registerPage = FXMLLoader.load(getClass().getResource("../FXML/doctorUI.fxml"));
        Scene registerScene = new Scene(registerPage);
        Stage setStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        setStage.setScene(registerScene);
        setStage.show();
    }

    public void setAppointment(ActionEvent actionEvent) throws SQLException, IOException {

        ConnectionClass connectionClass1 = new ConnectionClass();
        Connection connection2 = connectionClass1.getConnection();

        Statement scriptuser = connection2.createStatement();
        String appuser = "Insert into \"appointment\" (username, comments, date,\"myDoctor\") values "+
                "('"+Issues.getSelectionModel().getSelectedItem().getUserName().trim()+"','"+Issues.getSelectionModel().getSelectedItem().getComments().trim()+"',"+
                "'"+date.getValue()+"','"+alpha+"');";
        System.out.println(appuser);
        scriptuser.execute(appuser);
        System.out.println(appuser);
        connection2.close();
        removeFromIssues();
        Parent registerPage = FXMLLoader.load(getClass().getResource("../FXML/doctorUI.fxml"));
        Scene registerScene = new Scene(registerPage);
        Stage setStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        setStage.setScene(registerScene);
        setStage.show();
    }
    public void removeFromIssues() throws SQLException {
        ConnectionClass connectionClass1 = new ConnectionClass();
        Connection connection3 = connectionClass1.getConnection();
        Statement conn = connection3.createStatement();
        String rmuser = "Delete from issueslist where comments = '"+Issues.getSelectionModel().getSelectedItem().getComments().trim()+"';";
        System.out.println(rmuser);
        conn.execute(rmuser);
        conn.close();
    }
}