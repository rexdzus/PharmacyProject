package Controller;

import Connect.ConnectionClass;
import Main.Appointment;
import Main.DecisionTree;
import Main.ScriptList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class PatientUIController {

    public static String beta;
    public TextArea textArea;
    public Button feedback;
    public ComboBox comboBox;
    public RadioButton s_weight;
    public RadioButton s_headaches;
    public RadioButton s_neck;
    public RadioButton s_face;
    public RadioButton s_blocked;
    public RadioButton s_throat;
    public RadioButton s_sneezing;
    public RadioButton s_earpain;
    public RadioButton s_mucus;
    public TextArea displayDiagnosis;

    public TableView<Main.Appointment> Appointment;
    public TableColumn doctornameAps;
    public TableColumn commentsAps;
    public TableColumn datecol;
    public TableView<ScriptList> listScripts;
    public TableColumn medicinecol;
    public TableColumn pickcol;
    public TableColumn weekscol;

    ObservableList<String> severityList = FXCollections
            .observableArrayList("Severe", "Average", "Mild");
    public static final LocalDate initial_date = LocalDate.of(2000, 1, 1);
    private EventObject actionEvent;

    public void initialize() {
        comboBox.setItems(severityList);
        updateAppointment();
        updateScripts();
    }

    public String getDoctor(){
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connectionDoc = connectionClass.getConnection();
            Statement gettable = connectionDoc.createStatement();
            String getdoctor = "select * from patients where userName = '" + beta + "';";
            ResultSet doctor = gettable.executeQuery(getdoctor);
            String myDoctor = "";
            while(doctor.next()){
                myDoctor = doctor.getString("myDoctor");
            }
            connectionDoc.close();
            return myDoctor;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public void reportSymptom(ActionEvent actionEvent) throws SQLException {
        String symptoms = textArea.getText();;
        System.out.println(symptoms + " "+ comboBox.getValue());
        System.out.println(beta);
        System.out.println(getDoctor());

        ConnectionClass connection1 = new ConnectionClass();
        Connection connection2 = connection1.getConnection();

        Statement testuser = connection2.createStatement();
        String test1 = "Insert into issueslist (userName, severity, Comments, myDoctor) values " +
                "('"+beta+"','"+comboBox.getValue()+"','"+symptoms+"','"+getDoctor()+"');";
        testuser.executeUpdate(test1);
        connection2.close();
    }
    public void getDiagnosis(ActionEvent actionEvent) throws SQLException {
        String[] diag = DecisionTree.analise(s_weight, s_headaches, s_neck, s_face, s_blocked, s_throat, s_sneezing, s_earpain, s_mucus);

        System.out.println(diag[0]);
        displayDiagnosis.setText("Our AI has  found that you may have: " + diag[0] + ".\n" +
                "Is this diagnosis sounds incorrect please go to the diagnosis tab \n" +
                "and rate your pain and send a comment to your doctor. \n"+
                "\n"+
                "If this sounds correct your next steps are: \n" +
                diag[1]);

        createPrescription(getMedicine(diag[0]));

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        Statement testuser = connection.createStatement();
        String test1 = "UPDATE patients SET condition = '"+diag[0]+"' WHERE username = '"+beta+"';";
        System.out.println(beta);
        testuser.executeUpdate(test1);
        connection.close();

    }
    public void createPrescription(String medicine) throws SQLException {

        ConnectionClass connectionClass1 = new ConnectionClass();
        Connection connection2 = connectionClass1.getConnection();

        Statement scriptuser = connection2.createStatement();
        String script = "Insert into \"scriptList\" (username, medicine, date, amount, weeks, \"numberPill\") values "+
                "('"+beta+"','"+medicine+"','"+initial_date+"',0,0,0);";
        scriptuser.executeUpdate(script);
        connection2.close();

    }
    public String getMedicine(String ill) throws SQLException {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connectionMed = connectionClass.getConnection();
            Statement gettable = connectionMed.createStatement();
            String getMed = "select medicine from \"illnessMedicine\" where illness = '" + ill + "';";
            ResultSet med = gettable.executeQuery(getMed);
            String medicine = "";
            while(med.next()){
                medicine = med.getString("medicine");
            }
            System.out.println(medicine);
            connectionMed.close();
            return medicine;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }
    public void finalize(MouseEvent mouseEvent) {
        //String severity = comboBox.getSelectedItem();
    }
    public void updateAppointment(){
        try {
            doctornameAps.setCellValueFactory(new PropertyValueFactory<Appointment,String>("myDoctor"));
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
        String patientSQL = "select * from \"appointment\" where \"username\" = '" + beta + "';";
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
    public void updateScripts(){

        try {
            medicinecol.setCellValueFactory(new PropertyValueFactory<ScriptList,String>("medicine"));
            pickcol.setCellValueFactory(new PropertyValueFactory<ScriptList,String>("date"));
            weekscol.setCellValueFactory(new PropertyValueFactory<ScriptList,String>("weeks"));
            listScripts.getItems().setAll(parseScriptList());

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    List<ScriptList> listOfScripts = new ArrayList<ScriptList>();
    public List<ScriptList> parseScriptList() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connectionOg = connectionClass.getConnection();
        Statement gettable = connectionOg.createStatement();
        String pharmSQL = "select * from \"scriptList\" where \"username\" = '" + beta + "';";
        ResultSet st = gettable.executeQuery(pharmSQL);
        connectionOg.close();
        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list
        while(st.next()){
            String username = st.getString("username");
            String medicine = st.getString("medicine");
            Date date = st.getDate("date");
            String amount = st.getString("amount");
            String weeks = st.getString("weeks");
            String numberPill = st.getString("numberPill");
            ScriptList patL = new ScriptList(username,medicine,date,amount,weeks,numberPill);
            listOfScripts.add(patL);
        }
        return listOfScripts;
    }
}
