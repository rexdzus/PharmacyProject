package Controller;

import Main.IssuesList;
import Main.Patient;
import Connect.ConnectionClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    @FXML
    public TableColumn<Patient, String> firstnamecol;
    @FXML
    public TableColumn<Patient, String> lastnamecol;

    public TableView<IssuesList> Issues;
    public TableColumn usernamecol;
    public TableColumn severitycol;
    public TableColumn commentscol;
    public TextField condition_patient;


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
            for ( int i = 0; i<PatientsList.getItems().size(); i++) {
                PatientsList.getItems().clear();
            }
            firstnamecol.setCellValueFactory(new PropertyValueFactory<Patient,String>("firstname"));
            lastnamecol.setCellValueFactory(new PropertyValueFactory<Patient,String>("lastname"));
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
            String firstname = st.getString("firstname");
            String lastname = st.getString("lastname");
            String myDoctor = st.getString("myDoctor");
            Patient pat = new Patient(username,password,firstname,lastname,myDoctor);
            list.add(pat);

        }
        return list;
    }
    public void add_patient(ActionEvent actionEvent) throws SQLException, IOException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        Statement testuser = connection.createStatement();
        String test1 = "UPDATE patients SET myDoctor = '"+alpha+"' WHERE firstname = '"+getFirstName()+"' AND lastname = '"+getLastName()+"';";
        testuser.executeUpdate(test1);
        connection.close();
        updateTable();
    }
    public void delete_patient(ActionEvent actionEvent) throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        Statement testuser = connection.createStatement();
        String test1 = "UPDATE patients SET myDoctor = '' WHERE firstname = '"+getFirstName()+"' AND lastname = '"+getLastName()+"';";
        testuser.executeUpdate(test1);
        connection.close();
        updateTable();
    }

    public void setDiagnosis(ActionEvent actionEvent) {
        //select patient, type drug in dialog box, then send to new table to patients
        //on hitting diagnose button, the patient is removed from the issues list
        IssuesList selected = Issues.getSelectionModel().getSelectedItem();
        System.out.println("Selected p_id: "+selected.getP_id());

        ObservableList<IssuesList> selectedItems = IssuesList.getSelectedItems();

        selectedItems.addListener(new ListChangeListener<IssuesList>() {
            @Override
            public void onChanged(Change<? extends Issues> IssuesList) {
                System.out.println("Selection changed: " + IssuesList.getList());
            }
        })
    }

    public void setAppointment(ActionEvent actionEvent) {
    }
}