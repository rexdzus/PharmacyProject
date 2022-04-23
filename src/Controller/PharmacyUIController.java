package Controller;

import Main.*;
import Connect.ConnectionClass;

import Main.ScriptList;
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

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class PharmacyUIController implements Initializable {
    public TextField numberOfPills;
    public TextField lengthOfDosage;
    public TextField pillAmount;
    public DatePicker date;
    public CheckBox remind;

    /*    public TableColumn firstnamecol;
        public TableColumn lastnamecol;*/

    @FXML
    public Button deleteButton;
    public Button updateButton;


    public TableView<ScriptList> listScripts;
    public TableColumn usernamecol;
    public TableColumn medicinecol;
    public TableColumn datecol;
    public TableColumn amountcol;
    public TableColumn weekscol;
    public TableColumn numberPill;


    public void initialize (URL url, ResourceBundle rb){
        for ( int i = 0; i<listScripts.getItems().size(); i++) {
            System.out.print(listScripts.getItems().get(i).getUsername());
            listScripts.getItems().remove(i);
        }
        for ( int i = 0; i<listOfScripts.size(); i++) {
            System.out.print(listOfScripts.get(i).getUsername());
            listOfScripts.remove(i);
        }
        updateScripts();
    }

    public void updateScripts (){

        try {
            usernamecol.setCellValueFactory(new PropertyValueFactory<ScriptList,String>("username"));
            medicinecol.setCellValueFactory(new PropertyValueFactory<ScriptList,String>("medicine"));
            datecol.setCellValueFactory(new PropertyValueFactory<ScriptList,String>("date"));
            amountcol.setCellValueFactory(new PropertyValueFactory<ScriptList,String>("amount"));
            weekscol.setCellValueFactory(new PropertyValueFactory<ScriptList,String>("weeks"));
            numberPill.setCellValueFactory(new PropertyValueFactory<ScriptList,String>("numberPill"));
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
        String pharmSQL = "select * from \"scriptList\";";
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

    public void deleteScript(ActionEvent actionEvent) throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        Statement testuser = connection.createStatement();
        String test1 = ";";
        System.out.println(test1);
        testuser.executeUpdate(test1);
        connection.close();
    }

    public void updateScript(ActionEvent actionEvent) throws SQLException, IOException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement testuser = connection.createStatement();
        String test1 = "Update \"scriptList\" SET date = '"+getDate()+"', amount = '"+getAmount()+"'," +
                "weeks = '"+getWeeks()+"', \"numberPill\" = '"+getNumberPill()+"' WHERE username = '"+getUsername()+"';";
        System.out.println(test1);
        testuser.executeUpdate(test1);
        connection.close();
        System.out.print("UpdatedScript");
        Parent patientPage = FXMLLoader.load(getClass().getResource("../FXML/pharmacyUI.fxml"));
        Scene patientScene = new Scene(patientPage);
        Stage setStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        setStage.setScene(patientScene);
        setStage.show();
    }

    private String getNumberPill() {
        return numberOfPills.getText();
    }

    private String getWeeks() {
        return lengthOfDosage.getText();
    }

    private String getAmount() {
        return pillAmount.getText();
    }

    private LocalDate getDate(){
        System.out.println(date.getValue());
        return date.getValue();
    }

    private String getUsername() {
        ScriptList selectedItem = listScripts.getSelectionModel().getSelectedItem();
        return selectedItem.getUsername().trim();
    }
}