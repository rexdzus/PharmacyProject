package Controller;

import Connect.ConnectionClass;
import Controller.Controller;
import Main.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientUIController {

    public static String beta;
    public TextArea textArea;
    public Button feedback;
    public ComboBox comboBox;

    ObservableList<String> severityList = FXCollections
            .observableArrayList("Severe", "Average", "Mild");

    public void initialize() {
        comboBox.setItems(severityList);
    }

    public String getDoctor(){
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connectionDoc = connectionClass.getConnection();
            Statement gettable = connectionDoc.createStatement();
            System.out.println("made it 1");
            String getdoctor = "select * from patients where userName = '" + beta + "';";
            ResultSet doctor = gettable.executeQuery(getdoctor);
            String myDoctor = "";
            while(doctor.next()){
                myDoctor = doctor.getString("myDoctor");
            }
            System.out.println("made it 2");
            System.out.println(myDoctor);
            System.out.println("made it");
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
                "('"+beta+"','"+comboBox.getValue()+"','"+symptoms+"','jeff');";
        testuser.executeUpdate(test1);
        connection2.close();
    }

    public void finalize(MouseEvent mouseEvent) {
        //String severity = comboBox.getSelectedItem();
    }
}
